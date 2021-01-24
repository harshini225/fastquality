import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
  ArrayList<String> content;
  /**
   * Constructor
   */
  Parser() {
    this.content = new ArrayList<>();
  }

  /**
   * Helper
   *
   * @param c, a character
   * @return an int that symbolizes the quality score
   */
  public int convertToAscii(char c) {
    return ((int)(c) - 33);
  }

  /**
   * Trims reads' ends - based on meeting a condition at start
   *
   * @param seq, a character array of the sequence
   * @param quality, a character array of the sequence
   * @param t, tag number
   */
  private void trimWindow(char[] seq,  char[] quality, int t) {
    String newSequence = "";
    String newQuality = "";
    int startTracker = 0; // tracks if beginning or end
    int startIndex = 0;
    int endTracker = seq.length;
    boolean endPlaceHeld = false;

    for (int i = 0; i < seq.length; i++) {
      if ((convertToAscii(quality[i]) < 3) && (startTracker == 0)) {
        startIndex = i + 1;
      } else if (!(convertToAscii(quality[i]) < 3)) {
        startTracker = 1;
        endTracker = seq.length;
      } else if ((convertToAscii(quality[i]) < 3) && (startTracker == 1) && !(endPlaceHeld)) {
        endTracker = i;
        endPlaceHeld = true;
      }
      newSequence = newSequence + seq[i];
      newQuality = newQuality + quality[i];
    }
    newSequence = newSequence.substring(startIndex, endTracker);
    newQuality = newQuality.substring(startIndex, endTracker);

    System.out.println(newSequence);
    System.out.println(newQuality + "\n");
    content.add("@S" + Integer.toString(t));
    content.add(newSequence);
    content.add("+S" + Integer.toString(t));
    content.add(newQuality);
  }

  /**
   * Writes list to file
   */
  private void writeToFile(String fl) {
    try {
      FileWriter fw = new FileWriter(fl);
      BufferedWriter bw = new BufferedWriter(fw);
      for (String elt : content) {
        System.out.println(elt);
        bw.write(elt + "\n");
      }
      bw.close();
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Parses a fastq file
   *
   * @param fr: BufferedReader that corresponds to a fastq file
   */
  public void parse(BufferedReader fr) {
    char[] rawRead; // array of sequence into characters
    char[] rawQ;
    int tag = 0;

    try {
      String line = fr.readLine();
      while (line != null) {
        tag++;
        String n = line; //tag
        line = fr.readLine();
        String sq = line; // this is the sequence
        rawRead = sq.toCharArray();

        line = fr.readLine();
        line = fr.readLine(); // this is the quality sequence
        String q = line;
        rawQ = q.toCharArray();

        trimWindow(rawRead, rawQ, tag); // assign variable
        line = fr.readLine();
      }
      fr.close();
    } catch (IOException e) {
      System.out.println("Encountered an error: " + e.getMessage());
    }
  }

  /**
   * Main method
   *
   * @param args, command line arguments
   */
  public static void main(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("This program requires 2 arguments: "
              + "a fastq file, then an output file");
    }
    if (!args[0].endsWith(".fastq")) {
      throw new IllegalArgumentException("The first argument must be a fastq "
              + "file ");
    }

    try {
      BufferedReader fastqBR = new BufferedReader(new FileReader(args[0]));
      Parser myParser = new Parser();
      myParser.parse(fastqBR);
      myParser.writeToFile(args[1]);
      fastqBR.close();
    } catch (IOException e) {
      System.out.println("Encountered an error: " + e.getMessage());
    }
  }
}