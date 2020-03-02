/*
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 *
 *   Johan Boye, KTH, 2018
 */

package ir;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

/*
 *   Implements an inverted index as a hashtable on disk.
 *
 *   Both the words (the dictionary) and the data (the postings list) are
 *   stored in RandomAccessFiles that permit fast (almost constant-time)
 *   disk seeks.
 *
 *   When words are read and indexed, they are first put in an ordinary,
 *   main-memory HashMap. When all words are read, the index is committed
 *   to disk.
 */
public class PersistentHashedIndex implements Index {

    /** The directory where the persistent index files are stored. */
    public static final String INDEXDIR = "C:\\Users\\DiarS\\Desktop\\Skola-master\\Y4\\Search\\Labs\\index";

    /** The dictionary file name */
    public static final String DICTIONARY_FNAME = "dictionary";

    /** The dictionary file name */
    public static final String DATA_FNAME = "data";

    /** The terms file name */
    public static final String TERMS_FNAME = "terms";

    /** The doc info file name */
    public static final String DOCINFO_FNAME = "docInfo";

    /** The dictionary hash table on disk can fit this many entries. */
    public static final long TABLESIZE = 611953L;

    /** The dictionary hash table is stored in this file. */
    RandomAccessFile dictionaryFile;

    /** The data (the PostingsLists) are stored in this file. */
    RandomAccessFile dataFile;

    /** Pointer to the first free memory cell in the data file. */
    private long free = 0L;

    /** The cache as a main-memory hash map. */
    HashMap<String,PostingsList> index = new HashMap<String,PostingsList>();

    int entryLen = 13;



    // ===================================================================

    /**
     *   A helper class representing one entry in the dictionary hashtable.
     */
    public class Entry {

        public String token;
        int correctFirst = 1;
        private long dataPtr;
        private byte first = (byte) correctFirst;
        private int len;
        boolean valid = true;

        public Entry() {
        }

        public byte[] toByte() {
            ByteBuffer buffer = ByteBuffer.allocate(entryLen);
            buffer.put(first);
            buffer.putLong(Byte.BYTES, dataPtr);
            buffer.putInt(Byte.BYTES+Long.BYTES, len);
            return buffer.array();
        }

        public void toEntry(byte[] b) {

            ByteBuffer buffer = ByteBuffer.wrap(b, 0, Byte.BYTES);
            this.first = buffer.get();
            if(this.first != (byte) correctFirst) {
                this.valid = false;
            }
            buffer = ByteBuffer.wrap(b, Byte.BYTES, Long.BYTES);
            this.dataPtr = buffer.getLong();
            buffer = ByteBuffer.wrap(b, Byte.BYTES+Long.BYTES, Integer.BYTES);
            this.len = buffer.getInt();
        }


        public void set(int len) {
            this.len = len;
            this.dataPtr = free;
        }

        //
        //  YOUR CODE HERE
        //
    }


    // ==================================================================


    /**
     *  Constructor. Opens the dictionary file and the data file.
     *  If these files don't exist, they will be created.
     */
    public PersistentHashedIndex() {
        try {
            dictionaryFile = new RandomAccessFile( INDEXDIR + "\\" + DICTIONARY_FNAME, "rw" );
            dataFile = new RandomAccessFile( INDEXDIR + "\\" + DATA_FNAME, "rw" );
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        try {
            readDocInfo();
        } catch ( FileNotFoundException ignored) {
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     *  Writes data to the data file at a specified place.
     *
     *  @return The number of bytes written.
     */
    int writeData( String dataString, long ptr ) {
        try {
            dataFile.seek( ptr );
            byte[] data = dataString.getBytes();
            dataFile.write( data );
            return data.length;
        } catch ( IOException e ) {
            e.printStackTrace();
            return -1;
        }
    }


    /**
     *  Reads data from the data file
     */
    String readData( long ptr, int size ) {
        try {
            dataFile.seek( ptr );
            byte[] data = new byte[size];
            dataFile.readFully( data );
            return new String(data);
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }


    // ==================================================================
    //
    //  Reading and writing to the dictionary file.

    /*
     *  Writes an entry to the dictionary hash table file.
     *
     *  @param entry The key of this entry is assumed to have a fixed length
     *  @param ptr   The place in the dictionary file to store the entry
     */
    void writeEntry(Entry entry, long ptr) {
        //
        //  YOUR CODE HERE
        //

        try {
            dictionaryFile.seek( ptr );
            byte[] entryBytes = entry.toByte();
            dictionaryFile.write(entryBytes);
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     *  Reads an entry from the dictionary file.
     *
     *  @param ptr The place in the dictionary file where to start reading.
     */
    Entry readEntry(long ptr) {
        //
        //  REPLACE THE STATEMENT BELOW WITH YOUR CODE
        //

        Entry entry = new Entry();

        try {
            dictionaryFile.seek( ptr );
            byte[] data = new byte[entryLen];
            dictionaryFile.readFully( data );

            entry.toEntry(data);

            if(!entry.valid)
                return null;

            String postings = readData( entry.dataPtr, entry.len);

            if(postings == null) {
                return null;
            }
            entry.token = postings.split(";")[0];

        }
        catch (IOException e) {
            //e.printStackTrace();
            return null;
        }
        return entry;
    }


    // ==================================================================

    /**
     *  Writes the document names and document lengths to file.
     *
     * @throws IOException  { exception_description }
     */
    private void writeDocInfo() throws IOException {
        FileOutputStream fout = new FileOutputStream( INDEXDIR + "\\docInfo" );
        for (Map.Entry<Integer,String> entry : docNames.entrySet()) {
            Integer key = entry.getKey();
            String docInfoEntry = key + ";" + entry.getValue() + ";" + docLengths.get(key) + "\n";
            fout.write(docInfoEntry.getBytes());
        }
        fout.close();
    }


    /**
     *  Reads the document names and document lengths from file, and
     *  put them in the appropriate data structures.
     *
     * @throws     IOException  { exception_description }
     */
    private void readDocInfo() throws IOException {
        File file = new File( INDEXDIR + "\\docInfo" );
        FileReader freader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(freader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                docNames.put(new Integer(data[0]), data[1]);
                docLengths.put(new Integer(data[0]), new Integer(data[2]));
            }
        }
        freader.close();
    }


    /**
     *  Write the index to files.
     */
    public void writeIndex() {
        int collisions = 0;
        try {
            // Write the 'docNames' and 'docLengths' hash maps to a file
            writeDocInfo();

            // Write the dictionary and the postings list

            FileWriter writer = new FileWriter("C:\\Users\\DiarS\\Desktop\\Skola-master\\Y4\\Search\\Labs\\index\\dictionary");
            BufferedWriter dictWriter = new BufferedWriter(writer);

            long dataPtr;
            int postLen;
            int colli;
            for (Map.Entry<String, PostingsList> entry : index.entrySet()) {
                postLen = writeData(entry.getKey() + ";" + entry.getValue().toString(), free);

                Entry dictEntry = new Entry();
                dictEntry.set(postLen);
                free += postLen;

                colli = 0;
                dataPtr = hashKey(entry.getKey(), colli);

                while(readEntry(dataPtr) != null) {
                    colli++;
                    dataPtr = hashKey(entry.getKey(), colli);
                    collisions++;
                }

                writeEntry(dictEntry, dataPtr);
            }

            dictWriter.close();

        } catch ( IOException e ) {
            e.printStackTrace();
        }
        System.err.println( collisions + " collisions." );
    }


    public long hashKey(String token, int colli) {
        long ptr = 0L;

        for (int i = 0 ; i < token.length() ; i++) {
            ptr = ptr*101 + (int)token.charAt(i);
        }
        ptr = Math.abs(ptr);

        ptr = ptr % TABLESIZE;
        for(int i = 0; i < colli; i++) {
            ptr = (ptr + 1) % TABLESIZE;
        }

        return ptr*entryLen;
    }

    // ==================================================================


    /**
     *  Returns the postings for a specific term, or null
     *  if the term is not in the index.
     */
    public PostingsList getPostings( String token ) {
        //
        //  REPLACE THE STATEMENT BELOW WITH YOUR CODE
        //

        Entry entry;
        long ptr = hashKey(token, 0);

        int colli = 0;
        entry = readEntry(ptr);

        if(entry == null) {
            return null;
        }

        while(!entry.token.equals(token)) {
            colli++;
            ptr = hashKey(token, colli);
            entry = readEntry(ptr);
            if(entry == null) {
                return null;
            }
        }
        return dataTranslater2(readData(entry.dataPtr, entry.len));
    }


    public PostingsList dataTranslater2(String data) {
        PostingsList postingsList = null;
        String[] str = data.split(";");
        System.out.println(str);


        if (str.length > 0) {
            postingsList = new PostingsList();
            for (int i = 1; i < str.length; i++) {
                ArrayList<Integer> offsets = new ArrayList<>();
                System.out.println(str[i]);
                String[] entry = str[i].split(",");
                System.out.println(entry);

                for (int j = 1; j < entry.length; j++) {
                    offsets.add(Integer.valueOf(entry[j]));
                }
                System.out.println(entry[0].toString());

                postingsList.addPostEntryList(Integer.parseInt(entry[0]), offsets);
            }
        }
        return postingsList;
    }


    public PostingsList dataTranslater(String data) {
        Long start = System.currentTimeMillis();
        PostingsList pl = null;
        StringTokenizer offsetToken, postList = null;
        ArrayList<Integer> offsets;
        String buf;
        int docID;

        StringTokenizer docToken = new StringTokenizer(data);
        buf = docToken.nextToken();

        docToken = new StringTokenizer(data.substring(buf.length() + 1), ";");

        while (docToken.hasMoreTokens()) {

            postList = new StringTokenizer(docToken.nextToken());
            docID = Integer.parseInt(postList.nextToken());

            offsetToken = new StringTokenizer(postList.nextToken(), ",");


            offsets = new ArrayList<>();


            while (offsetToken.hasMoreTokens()) {
                offsets.add(Integer.parseInt(offsetToken.nextToken()));
            }


            if (pl == null) {

                pl = new PostingsList();

            } else {
                pl.addPostEntryList(docID, offsets);
            }


        }
        System.out.println("This is the time for translating: " + (System.currentTimeMillis() - start));


        return pl;
    }


    /**
     *  Inserts this token in the main-memory hashtable.
     */
    public void insert( String token, int docID, int offset ) {
        {
            if (index.get(token) != null) {
                PostingsList pl = index.get(token);
                pl.addPostEntry(docID, offset);
                index.put(token, pl);

            } else {
                PostingsList pl = new PostingsList();
                pl.addPostEntry(docID, offset);
                index.put(token, pl);
            }
        }
    }


    /**
     *  Write index to file after indexing is done.
     */
    public void cleanup() {
        System.err.println( index.keySet().size() + " unique words" );
        System.err.print( "Writing index to disk..." );
        writeIndex();
        System.err.println( "done!" );
    }
}

//
///*
// *   This file is part of the computer assignment for the
// *   Information Retrieval course at KTH.
// *
// *   Johan Boye, KTH, 2018
// */
//
//package ir;
//
//import java.io.*;
//import java.util.*;
//import java.nio.charset.*;
//
//
///*
// *   Implements an inverted index as a hashtable on disk.
// *
// *   Both the words (the dictionary) and the data (the postings list) are
// *   stored in RandomAccessFiles that permit fast (almost constant-time)
// *   disk seeks.
// *
// *   When words are read and indexed, they are first put in an ordinary,
// *   main-memory HashMap. When all words are read, the index is committed
// *   to disk.
// */
//public class PersistentHashedIndex implements Index {
//
//    /** The directory where the persistent index files are stored. */
//    public static final String INDEXDIR = "./index";
//
//    /** The dictionary file name */
//    public static final String DICTIONARY_FNAME = "dictionary";
//
//    /** The dictionary file name */
//    public static final String DATA_FNAME = "data";
//
//    /** The terms file name */
//    public static final String TERMS_FNAME = "terms";
//
//    /** The doc info file name */
//    public static final String DOCINFO_FNAME = "docInfo";
//
//    /** The dictionary hash table on disk can fit this many entries. */
//    public static final long TABLESIZE = 611953L;
//
//    /** The dictionary hash table is stored in this file. */
//    RandomAccessFile dictionaryFile;
//
//    /** The data (the PostingsLists) are stored in this file. */
//    RandomAccessFile dataFile;
//
//    /** Pointer to the first free memory cell in the data file. */
//    long free = 0L;
//
//    /** The cache as a main-memory hash map. */
//    HashMap<String,PostingsList> index = new HashMap<String,PostingsList>();
//
//
//    // ===================================================================
//
//    /**
//     *   A helper class representing one entry in the dictionary hashtable.
//     */
//    public class Entry {
//        //
//        //  YOUR CODE HERE
//        //
//    }
//
//
//    // ==================================================================
//
//
//    /**
//     *  Constructor. Opens the dictionary file and the data file.
//     *  If these files don't exist, they will be created.
//     */
//    public PersistentHashedIndex() {
//        try {
//            dictionaryFile = new RandomAccessFile( INDEXDIR + "/" + DICTIONARY_FNAME, "rw" );
//            dataFile = new RandomAccessFile( INDEXDIR + "/" + DATA_FNAME, "rw" );
//        } catch ( IOException e ) {
//            e.printStackTrace();
//        }
//
//        try {
//            readDocInfo();
//        } catch ( FileNotFoundException e ) {
//        } catch ( IOException e ) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     *  Writes data to the data file at a specified place.
//     *
//     *  @return The number of bytes written.
//     */
//    int writeData( String dataString, long ptr ) {
//        try {
//            dataFile.seek( ptr );
//            byte[] data = dataString.getBytes();
//            dataFile.write( data );
//            return data.length;
//        } catch ( IOException e ) {
//            e.printStackTrace();
//            return -1;
//        }
//    }
//
//
//    /**
//     *  Reads data from the data file
//     */
//    String readData( long ptr, int size ) {
//        try {
//            dataFile.seek( ptr );
//            byte[] data = new byte[size];
//            dataFile.readFully( data );
//            return new String(data);
//        } catch ( IOException e ) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    // ==================================================================
//    //
//    //  Reading and writing to the dictionary file.
//
//    /*
//     *  Writes an entry to the dictionary hash table file.
//     *
//     *  @param entry The key of this entry is assumed to have a fixed length
//     *  @param ptr   The place in the dictionary file to store the entry
//     */
//    void writeEntry( Entry entry, long ptr ) {
//        //
//        //  YOUR CODE HERE
//        //
//    }
//
//    /**
//     *  Reads an entry from the dictionary file.
//     *
//     *  @param ptr The place in the dictionary file where to start reading.
//     */
//    Entry readEntry( long ptr ) {
//        //
//        //  REPLACE THE STATEMENT BELOW WITH YOUR CODE
//        //
//        return null;
//    }
//
//
//    // ==================================================================
//
//    /**
//     *  Writes the document names and document lengths to file.
//     *
//     * @throws IOException  { exception_description }
//     */
//    private void writeDocInfo() throws IOException {
//        FileOutputStream fout = new FileOutputStream( INDEXDIR + "/docInfo" );
//        for (Map.Entry<Integer,String> entry : docNames.entrySet()) {
//            Integer key = entry.getKey();
//            String docInfoEntry = key + ";" + entry.getValue() + ";" + docLengths.get(key) + "\n";
//            fout.write(docInfoEntry.getBytes());
//        }
//        fout.close();
//    }
//
//
//    /**
//     *  Reads the document names and document lengths from file, and
//     *  put them in the appropriate data structures.
//     *
//     * @throws     IOException  { exception_description }
//     */
//    private void readDocInfo() throws IOException {
//        File file = new File( INDEXDIR + "/docInfo" );
//        FileReader freader = new FileReader(file);
//        try (BufferedReader br = new BufferedReader(freader)) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(";");
//                docNames.put(new Integer(data[0]), data[1]);
//                docLengths.put(new Integer(data[0]), new Integer(data[2]));
//            }
//        }
//        freader.close();
//    }
//
//
//    /**
//     *  Write the index to files.
//     */
//    public void writeIndex() {
//        int collisions = 0;
//        try {
//            // Write the 'docNames' and 'docLengths' hash maps to a file
//            writeDocInfo();
//
//            // Write the dictionary and the postings list
//
//            //
//            //  YOUR CODE HERE
//            //
//        } catch ( IOException e ) {
//            e.printStackTrace();
//        }
//        System.err.println( collisions + " collisions." );
//    }
//
//
//    // ==================================================================
//
//
//    /**
//     *  Returns the postings for a specific term, or null
//     *  if the term is not in the index.
//     */
//    public PostingsList getPostings( String token ) {
//        //
//        //  REPLACE THE STATEMENT BELOW WITH YOUR CODE
//        //
//        return null;
//    }
//
//
//    /**
//     *  Inserts this token in the main-memory hashtable.
//     */
//    public void insert( String token, int docID, int offset ) {
//        //
//        //  YOUR CODE HERE
//        //
//    }
//
//
//    /**
//     *  Write index to file after indexing is done.
//     */
//    public void cleanup() {
//        System.err.println( index.keySet().size() + " unique words" );
//        System.err.print( "Writing index to disk..." );
//        writeIndex();
//        System.err.println( "done!" );
//    }
//}
