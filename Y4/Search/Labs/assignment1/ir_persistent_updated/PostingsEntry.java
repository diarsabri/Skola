/*
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 *
 *   Johan Boye, 2017
 */

package ir;

import java.util.ArrayList;
import java.io.Serializable;

public class PostingsEntry implements Comparable<PostingsEntry>, Serializable {

    public int docID;
    public double score = 0;
    ArrayList<Integer> positions = new ArrayList<>();

    /**
     * PostingsEntries are compared by their score (only relevant
     * in ranked retrieval).
     * <p>
     * The comparison is defined so that entries will be put in
     * descending order.
     */
    public int compareTo(PostingsEntry other) {
        return Double.compare(other.score, score);
    }

    public PostingsEntry(int docID) {
        this.docID = docID;
    }

    public void addOffset(int offset) {
        positions.add(offset);
    }

    public void addOffsetList(ArrayList<Integer> offsets) {
        positions = offsets;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(docID + ",");
        for (int pos : positions) {
            sb.append(pos + ",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.append(";").toString();
    }

}

