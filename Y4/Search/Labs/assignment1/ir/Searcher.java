/*
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 *
 *   Johan Boye, 2017
 */

package ir;
import java.util.*;

/**
 *  Searches an index for results of a query.
 */
public class Searcher {

    /** The index to be searched by this Searcher. */
    Index index;

    /** The k-gram index to be searched by this Searcher */
    KGramIndex kgIndex;

    /** Constructor */
    public Searcher( Index index, KGramIndex kgIndex ) {
        this.index = index;
        this.kgIndex = kgIndex;
    }

    /**
     *  Searches the index for postings matching the query.
     *  @return A postings list representing the result of the query.
     */
    public PostingsList search( Query query, QueryType queryType, RankingType rankingType ) {
        ArrayList<Query.QueryTerm> listOfTerms = query.queryterm;
        LinkedList<PostingsList> linkedPostings = new LinkedList<>();

        for (Query.QueryTerm term: listOfTerms) {
            linkedPostings.add(index.getPostings(term.term));
        }

        PostingsList result;

        if (queryType == QueryType.INTERSECTION_QUERY) {
            result = linkedPostings.pop();


            while(!linkedPostings.isEmpty() & result != null) {
                result = intersect(result, linkedPostings.pop());
            }
        } else if (queryType == QueryType.PHRASE_QUERY){
            result = linkedPostings.pop();
            for (PostingsList linkedPosting : linkedPostings) {
                Collections.sort(linkedPosting.list);
            }
            while(!linkedPostings.isEmpty() & result != null) {
                result = phraseQuery(result, linkedPostings.pop());
            }
        } else {
            result = rankedQuery(linkedPostings);
        }

        return result;
    }

    public PostingsList intersect(PostingsList p1,PostingsList p2) {
        PostingsList result = new PostingsList();
        PostingsEntry firstPosting, secondPosting;
        int i = 0;
        int j = 0;
        while (i < p1.size() && j < p2.size()) {
            firstPosting = p1.get(i);
            secondPosting = p2.get(j);
            if (firstPosting.docID == secondPosting.docID) {
                result.addPostEntry(firstPosting.docID, 0);
                i++;
                j++;
            } else if (firstPosting.docID < secondPosting.docID) {

                i++;
            } else
                j++;
        }
        return result;
    }

    public PostingsList phraseQuery(PostingsList PL1, PostingsList PL2){
        PostingsList answer = new PostingsList();
        int pi,pj,pos1,pos2;
        int i = 0;
        int j = 0;

        PostingsEntry p1, p2;
        ArrayList<Integer> pp1 = new ArrayList<Integer>();
        ArrayList<Integer> pp2 = new ArrayList<Integer>();

        while(i < PL1.size() && j < PL2.size()){
            p1 = PL1.get(i);
            p2 = PL2.get(j);
            if(p1.docID == p2.docID){
                pi = 0; pj = 0;
                pp1 = p1.positions;
                pp2 = p2.positions;

                while (pi<pp1.size()){
                    while (pj<pp2.size()){
                        pos1 = pp1.get(pi);
                        pos2 = pp2.get(pj);

                        if(pos2-pos1==1){
                            answer.addPostEntry(p1.docID,pos2);
                        } else if(pos2>pos1) {
                            break;
                        }
                        pj++;
                    }
                    pi++;
                };
                i++;
                j++;
            }
            else if(p1.docID < p2.docID){
                i++;
            }
            else j++;
        }
        return answer;
    }

    public PostingsList rankedQuery(LinkedList<PostingsList> postingsLists) {
        int n = index.docLengths.size();
        HashMap<Integer, PostingsEntry> entries = new HashMap<>();
        int count = 0;
        for (PostingsList p : postingsLists) {
            for (PostingsEntry d : p.list) {
                if (!entries.containsKey(d.docID)) {
                    entries.put(d.docID, new PostingsEntry(d.docID));
                }
                entries.get(d.docID).score += (d.positions.size() * Math.log((double) n/p.size())) / index.docLengths.get(d.docID);
            }
        }
        PostingsList tmp = new PostingsList();
        tmp.list.addAll(entries.values());
        Collections.sort(tmp.list);

        return tmp;
    }


}