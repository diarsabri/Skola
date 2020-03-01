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
    ArrayList<Integer> positions = new ArrayList<>();
    public int skip = (int) Math.round(Math.sqrt(positions.size()));

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
    
    public PostingsEntry(int docID) {
        this.docID = docID;
        //this.score = score;
    }

    public void addOffset(int offset) {
        positions.add(offset);
    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }

    public boolean hasSkip(int i) {
        if (i + skip < positions.size() && skip != 0) {
            return 0 == positions.size() % skip;
        }
        return false;
    }

    public int getSkip(int i) {
        return positions.get(i + skip);
    }
    
    public void addOffsetList(ArrayList<Integer> offsets) {
        positions = offsets;
    }
}

