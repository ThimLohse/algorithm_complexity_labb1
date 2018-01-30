/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurssidan http://www.csc.kth.se/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Main {

  public static int[][] memory = new int[41][41];

  public static List<String> readWordList(BufferedReader input) throws IOException {
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
    //long t1 = System.currentTimeMillis();

    //File f = new File("/Users/thim/Documents/Projects/Words/src/ordlistamedfel.txt");
    initMatrix();
    //BufferedReader stdin = new BufferedReader(new FileReader(f));
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
    // Säkrast att specificera att UTF-8 ska användas, för vissa system har annan
    // standardinställning för teckenkodningen.
    List<String> wordList = readWordList(stdin);
    OutputStream out = new BufferedOutputStream( System.out );
    String word;
    while ((word = stdin.readLine()) != null) {
      ClosestWords closestWords = new ClosestWords(word, wordList);
      out.write((word + " (" + closestWords.getMinDistance()+ ")").getBytes());
      for (String w : closestWords.getClosestWords())
        out.write((" " + w).getBytes());
      out.write(("\n").getBytes());
    }
    out.flush();
        //long tottime = (System.currentTimeMillis() - t1);
        //System.out.println("CPU time: " + tottime + " ms");

  }
  static void initMatrix(){
    for (int i = 0; i < 41; i++) {
      memory[i][0] = i;
      memory[0][i] = i;
    }
  }
  static void printMatrix(){
    for (int i = 0; i < 41; i++) {
      for (int j = 0; j < 41; j++) {
        System.out.print(memory[i][j]);
      }
      System.out.println();

    }
  }
}
