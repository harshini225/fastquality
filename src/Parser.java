import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
  /**
   * Constructor
   */
  private Parser() {
  }

  /**
   * Converts text from char to ASCII quality scores
   *
   * @param text, a String
   * @return a String with all of the scores .... TODO: change this
   */
  private String fastConvert(String text) {
    ArrayList<Integer> myPairs = new ArrayList<>();
    char[] myChars = text.toCharArray();
    StringBuilder result = new StringBuilder("Quality scores: ");

    for (char c: myChars) {
      int newInt = c; // uniform gc content
      result.append(newInt - 33).append(" "); // approaches - window threshold, trim if threshold hits a certain amount
    }
    return result.toString();
  }

  /**
   * Parses a fastq file
   *
   * @param fr: BufferedReader that corresponds to a fastq file
   */
  public void parse(BufferedReader fr) {
    try {
      String line = fr.readLine();
      while (line != null) {
        String n = line; // this is the tag
        line = fr.readLine();
        String sq = line; // this is the sequence
        // ReadQ seq = new ReadQ(n, sq, "", "", "");
        // lst.add(seq);

        line = fr.readLine();
        line = fr.readLine(); // this is the quality sequence
        // seq.setQual(line);
        String scores = fastConvert(line); // quality sequence de-ascii-fied
        System.out.println(scores + "\n");
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
    if (args.length != 1) {
      throw new IllegalArgumentException("This program requires 2 arguments: "
              + "a fastq file, then an output file"); // TODO
    }

    if (!args[0].endsWith(".fastq")) {
      throw new IllegalArgumentException("The first argument must be a fastq "
              + "file ");
    }

    try {
      BufferedReader fastqBR = new BufferedReader(new FileReader(args[0]));
      Parser myParser = new Parser();
      myParser.parse(fastqBR);
    } catch (IOException e) {
      System.out.println("Encountered an error: " + e.getMessage());
    }
  }
}