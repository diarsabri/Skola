/*  
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 * 
 *   Johan Boye, 2017
 */  

package ir;
import java.util.*;
import java.lang.*;

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

    public PostingsList PositionalIntersect(PostingsList P1, PostingsList P2, int k){
        PostingsList answer = new PostingsList();

        PostingsEntry p1, p2, pnew;
        int i,j,pi,pj,pos1,pos2;
        i=0;
        j=0;

        
        
        ArrayList<Integer> l,pp1,pp2,ppnew;

        pp1 = new ArrayList<Integer>();
        pp2 = new ArrayList<Integer>();

        while(i<P1.size() && j<P2.size()){

            p1 = P1.get(i);
            p2 = P2.get(j);

            if(p1.getId() == p2.getId()){
                pi = 0;
                pj = 0;
                pp1 = p1.getOffsets();
                pp2 = p2.getOffsets();              

                l = new ArrayList<Integer>();
                

                while(pi<pp1.size()){
                    
                    

                    while(pj<pp2.size()){
                        pos1 = pp1.get(pi);
                        pos2 = pp2.get(pj);

                        if(pos2-pos1==k){
                            answer.addToPostingsList(p2.getId(),pos2);            
                        }
                        else if(pos2>pos1){
                            break;
                        }                        
                        pj++;
                    }
                    pi++;
                }; 
                i++;
                j++;
            } //END OF MATCHING IDS
            else if(p1.getId()<p2.getId()){
                i++;
            }
            else{
                j++;
            }
        }
        return answer;
    }

    public PostingsList intersect(PostingsList P1, PostingsList P2){
        PostingsList answer = new PostingsList();        
        PostingsEntry p1, p2;
        int i,j;
        i=0;
        j=0;
        p1 = P1.get(i);
        p2 = P2.get(j);
        ArrayList<Integer> offsetList;
        while(p1 != null && p2!=null){
            if(p1.getId() == p2.getId()){
                offsetList = p1.getOffsets();
                answer.addToPostingsList(p1.getId(), offsetList);
                System.out.println("p1 get id");
                System.out.println(p1.getId());
                i++;
                j++;
                if(i== P1.size()){p1 = null;}
                else{p1 = P1.get(i);}
                if(j== P2.size()){p2 = null;}
                else{p2 = P2.get(j);}
            }
            else if(p1.getId()<p2.getId()){

                i++;
                if(i== P1.size()){p1 = null;}
                else{p1 = P1.get(i);}              
            }
            else{
                j++;
                if(j== P2.size()){p2 = null;}
                else{p2 = P2.get(j);} 
            }
        }
        return answer;
    }
    public ArrayList<String> SortByIncreasingFrequency(ArrayList<String> terms){

        //document frequency = postingslength.size()
        ArrayList<String> sorted = new ArrayList<String>();

        int currentorgsize;
        String currentorg;
        boolean added;
        sorted.add(terms.get(0));


        for(int i = 1; i< terms.size();i++){
            added=false;
            currentorg = terms.get(i);
            currentorgsize = index.getPostings(terms.get(i)).size();

            for(int j = 0 ; j< sorted.size(); j++){
            
                if(currentorgsize > index.getPostings(sorted.get(j)).size()){
                    sorted.add(j, currentorg);
                    added = true;

                }

            }
            if(!added){
                sorted.add(currentorg);
            }
        }

        return sorted;

    }


    /**
     *  Searches the index for postings matching the query.
     *  @return A postings list representing the result of the query.
     */
    public PostingsList search( Query query, QueryType queryType, RankingType rankingType ) { 
        //
        //  REPLACE THE STATEMENT BELOW WITH YOUR CODE
        //
        
        PostingsList P1, P2;
        P1 = new PostingsList();
        P2 = new PostingsList();

        ArrayList<String> terms = query.getTerms();

        P1 = index.getPostings(terms.get(0));

        //INTERSECTION_QUERY, PHRASE_QUERY, RANKED_QUERY 

        if(queryType == QueryType.INTERSECTION_QUERY){
            for(int i=1; i < terms.size(); i++){
                
                P2 = index.getPostings(terms.get(i));
    
                P1 = intersect(P1, P2);
            }
        }
        else if(queryType == QueryType.PHRASE_QUERY){
            for(int i=1; i < terms.size(); i++){
                    
                P2 = index.getPostings(terms.get(i));
                P1 = PositionalIntersect(P1, P2, 1);
                
            }
        }

        return P1;
    }
}