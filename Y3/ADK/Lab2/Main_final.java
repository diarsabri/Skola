import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

  public static List<String> readWordList(BufferedReader input) throws IOException { //read file until #
    LinkedList<String> list = new LinkedList<String>();
    while (true) {
      String s = input.readLine();
      if (s.equals("#"))
        break;
      list.add(s);
    }
    return list;
  }

  public static void main(String args[]) throws IOException {
    int[][] matrix = new int[41][41]; 
    for(int i = 0; i < 41; i++) { 
      matrix[0][i] = i; 
      matrix[i][0] = i; 
    }
  
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
   
    List<String> wordList = readWordList(stdin); 
    String word;
    while ((word = stdin.readLine()) != null) {
      ClosestWords closestWords = new ClosestWords(word, wordList, matrix); 
      System.out.print(word + " (" + closestWords.getMinDistance() + ")"); 
      for (String w : closestWords.getClosestWords()) 
        System.out.print(" " + w);
      System.out.println();
    }


  }
}