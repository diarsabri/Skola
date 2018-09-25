/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords2 {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;
  String previousWord = "";
  int sameLetters = 0;

  int newDist(String w1,String w2,int[][] v) {
    int x = w1.length()+1;
    int y = w2.length()+1;
    sameLetters = matchingLetters(w2,previousWord);
  	for(int n = 1; n < x; n++) {
  		for (int m = sameLetters+1; m < y; m++ ) {
        if(w1.charAt(n-1) == w2.charAt(m-1)) { //if char at position n in word 1 is same as position m in word 2
					v[n][m] = minimum(v[n-1][m-1],v[n-1][m]+1,v[n][m-1]+1); 
				} else {
					v[n][m] = 1+minimum(v[n-1][m-1],v[n-1][m],v[n][m-1]); 
				}
  		}
  	}
    previousWord = w2;
    return v[x-1][y-1];
  }

  int matchingLetters(String w1,String w2) {
    int len = Math.min(w1.length(),w2.length());
    for(int i = 0; i < len; i++) {
      if(w1.charAt(i) != w2.charAt(i)) {
        return i;
      }
    }
    return len;
  } 

  int minimum(int a, int b, int c) {
    return Math.min(Math.min(a,b),c);
  }

  public ClosestWords2(String w, List<String> wordList, int[][] v) {
    previousWord = "";
    int dist;
    for (String s : wordList) {		

      dist = newDist(w, s, v);
      //System.out.println("d(" + w + "," + s + ")=" + dist);
      //System.out.println();

      
      if (dist < closestDistance || closestDistance == -1) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      }
      else if (dist == closestDistance)
        closestWords.add(s); 
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
