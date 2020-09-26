/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;
  String prevWord = "";
  int noDiff = 0;
  int closestDistance = -1;

  int partDist(String w1, String w2, int[][] matrix) {
    int w1Length = w1.length(); 
    int w2Length = w2.length();
    if(Math.abs(w1Length-w2Length) > closestDistance && closestDistance != -1) {
      return closestDistance+1;
    }
    int noDiff = sameLetter(w2, prevWord); 
    for(int i = 1; i < w1Length+1; i++) {
      for(int m = noDiff + 1; m < w2Length+1; m++){
        if(w1.charAt(i-1) == w2.charAt(m-1)){
          matrix[i][m] = Math.min(Math.min(matrix[i-1][m-1],matrix[i-1][m]+1), matrix[i][m-1]+1);
        }
        else{
          matrix[i][m] = 1 + Math.min(Math.min(matrix[i-1][m-1],matrix[i-1][m]), matrix[i][m-1]);
        }

      }
    }
   prevWord = w2;
   return matrix[w1Length][w2Length]; 
  }

  public int sameLetter(String w1, String w2){
    int wordLength = Math.min(w1.length(), w2.length());
    for (int i = 0; i<wordLength; i++){
      if (w1.charAt(i) != w2.charAt(i)){
        return i;
      } 
    }
   return wordLength; 
  }
  

  public ClosestWords(String w, List<String> wordList, int[][] matrix) {
    prevWord = "";
    int dist;
    for (String s : wordList) {
      dist = partDist(w, s, matrix); 
      
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
