/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */

import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;
  int additionCost = 1;
  int deletionCost = 1;
  int replacementCost;

  String previousWord;

  int partDist(String w1, String w2, int m, int n) {

    int numEqual = 0;

        int minLen = n > previousWord.length() ? previousWord.length() : n;
        //Check for already calculated characters in previous word compared to new word
        for (int i = 0; i < minLen; i++) {
          if(w2.charAt(i) == previousWord.charAt(i))
            numEqual++;
          else
            break;
        }



    for (int col = numEqual+1; col <= n; col++) {

      //we know that position [0][0] is always 0 so we can skip this position
      for (int row = 1; row <= m; row++) {

        if(w1.charAt(row-1) == w2.charAt(col-1)){
          replacementCost = 0;
        }else {
          replacementCost = 1;
        }

        Main.memory[row][col] = minOperation(Main.memory[row-1][col] + additionCost, Main.memory[row][col-1] + deletionCost, Main.memory[row-1][col-1] + replacementCost);
      }

    }
    return Main.memory[m][n];
  }

  int Distance(String w1, String w2) {
    return partDist(w1, w2, w1.length(), w2.length());
  }

  public ClosestWords(String w, List<String> wordList) {
    previousWord = "";
    for (String s : wordList) {

      int dist = Distance(w, s);

      //When 's' calculated, 's' is the new previous word up for comparison.
      previousWord = s;

      //System.out.println("d(" + w + "," + s + ")=" + dist);
      if (dist < closestDistance || closestDistance == -1) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      }
      else if (dist == closestDistance)
        closestWords.add(s);
    }
  }
  int minOperation(int a, int b, int c){

    return Math.min(Math.min(a,b),c);
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }

}
