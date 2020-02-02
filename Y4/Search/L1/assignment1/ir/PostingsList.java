/*  
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 * 
 *   Johan Boye, 2017
 */  

package ir;

import java.util.*;

public class PostingsList {
    
    /** The postings list */
    private ArrayList<PostingsEntry> list = new ArrayList<PostingsEntry>();
    private HashMap<Integer, PostingsEntry> docMap = new HashMap<Integer, PostingsEntry>();


    /** Number of postings in this list. */
    public int size() {
    return list.size();
    }

    /** Returns the ith posting. */
    public PostingsEntry get( int i ) {
    return list.get( i );
    }

    // 
    //  YOUR CODE HERE
    //
    public void printPostings(){
        System.err.println("size "+list.size());
        for(int j = 0; j<list.size();j++){
            System.err.println(list.get(j));
        }
    }

    public void addToPostingsList(int docID, int offset){
        if(docMap.containsKey(docID)){
            PostingsEntry entry = docMap.get(docID);
            entry.addOffset(offset);
        }
        else{
            PostingsEntry entry = new PostingsEntry(docID, offset);
            docMap.put(docID, entry);
            list.add(entry);
        }
    }
    public void addToPostingsList(int docID, ArrayList<Integer> offsets){
        if(docMap.containsKey(docID)){
            PostingsEntry entry = docMap.get(docID);
            entry.setOffsets(offsets);
        }
        else{
            PostingsEntry entry = new PostingsEntry(docID);
            entry.setOffsets(offsets);
            docMap.put(docID, entry);
            list.add(entry);
        }
    }
    public void addToPostingsList(PostingsEntry thePosting, int offset){

        if(docMap.containsKey(thePosting.docID)){
            PostingsEntry entry = docMap.get(thePosting.docID);
            entry.addOffset(offset);
        }
        else{
            PostingsEntry entry = new PostingsEntry(thePosting.docID, offset);
            docMap.put(thePosting.docID, entry);
            list.add(entry);
        } 
    }

}

