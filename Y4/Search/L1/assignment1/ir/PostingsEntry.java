/*  
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 * 
 *   Johan Boye, 2017
 */  

package ir;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.io.Serializable;

public class PostingsEntry implements Comparable<PostingsEntry>, Serializable {

    public int docID;
    public double score = 0;
    public ArrayList<Integer> offsets = new ArrayList<Integer>();

    /**
     *  PostingsEntries are compared by their score (only relevant
     *  in ranked retrieval).
     *
     *  The comparison is defined so that entries will be put in 
     *  descending order.
     */
    public int compareTo( PostingsEntry other ) {
       return Double.compare( other.score, score );
    }


    //
    // YOUR CODE HERE
    //
    public PostingsEntry(int theDocID){
        this.docID = theDocID;
    }

    public ArrayList<Integer> getOffsets(){
        return this.offsets;
    }

    public void setOffsets(ArrayList<Integer> offsets){
        for(int j = 0 ; j< offsets.size();j++){
            this.offsets.add(offsets.get(j));
        }
    }
    public PostingsEntry(int theDocID, int theOffset){
        this.docID = theDocID;
        this.offsets.add(theOffset);
    }
    public void addOffset(int offset){
        if(!this.offsets.contains(offset)){
            this.offsets.add(offset);
        }
        
    }
    public int getId(){
        return this.docID;
    }

}

