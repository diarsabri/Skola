/*  
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 * 
 *   Johan Boye, 2017
 */  


package ir;

import java.util.HashMap;
import java.util.Iterator;


/**
 *   Implements an inverted index as a Hashtable from words to PostingsLists.
 */
public class HashedIndex implements Index {


    /** The index as a hashtable. */
    private HashMap<String,PostingsList> index = new HashMap<String,PostingsList>();


    /**
     *  Inserts this token in the hashtable.
     *  D
     */
    public void insert( String token, int docID, int offset ) {
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


    /**
     *  Returns the postings for a specific term, or null
     *  if the term is not in the index.
     *  D
     */
    public PostingsList getPostings( String token ) {
        return index.get(token);
    }

    //D
    public HashMap<String, PostingsList> getIndex() {
        return index;
    }

    /**
     *  No need for cleanup in a HashedIndex.
     */
    public void cleanup() {
    }
}
