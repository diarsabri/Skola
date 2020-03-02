/*  
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 * 
 *   Johan Boye, 2017
 */  

package ir;

import java.util.ArrayList;
import java.util.HashMap;


public class PostingsList {
    
    /** The postings list */
    public ArrayList<PostingsEntry> list = new ArrayList<PostingsEntry>();
    public HashMap<Integer, PostingsEntry> docIDs = new HashMap<>();
    public int skip = (int) Math.round(Math.sqrt(list.size()));

    public void addPostEntry(int docID, int offset) {
        if (docIDs.containsKey(docID)) {
            PostingsEntry entry = docIDs.get(docID);
            entry.addOffset(offset);
        } else {
            PostingsEntry entry = new PostingsEntry(docID);
            entry.addOffset(offset);
            docIDs.put(docID, entry);
            list.add(entry);
        }
    }

    /** Number of postings in this list. */
    public int size() {
        return list.size();
    }

    /** Returns the ith posting. */
    public PostingsEntry get( int i ) {
        return list.get( i );
    }

    public void addPostEntryList(int docID, ArrayList<Integer> offsets) {
        if (docIDs.containsKey(docID)) {
            PostingsEntry entry = docIDs.get(docID);
            entry.addOffsetList(offsets);
        } else {
            PostingsEntry entry = new PostingsEntry(docID);
            entry.addOffsetList(offsets);
            docIDs.put(docID, entry);
            list.add(entry);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (PostingsEntry entry : list) {
            sb.append(entry.toString());
        }
        return sb.toString();
    }

}

