
import java.util.HashSet;
import java.util.ArrayList;
import java.io.*;

public class TBDSTester {
  public static void main(String[] args) throws FileNotFoundException {
    HashSet<String> words = getWords();

    TBDSInterface tbds = new TBDS();

    //Add all words to tbds
    for(String word: words){
      tbds.add(word, word);
    }

    int countErrors = 0;
    int totalErrors = 0;

    //Check that the tbds contains all words
    for(String s: words){
      if(!tbds.containsKey(s)){
        countErrors++;
      }
    }
    System.out.println("Number of missed keys after searching all words: " + countErrors);

    totalErrors += countErrors;
    countErrors = 0;
    int countErrors1 = 0;

    //Check that the correct value is associated with each key
    for(String s: words){
      if(!tbds.containsKey(s)){
        countErrors++;
      }
      if(!tbds.get(s).equals(s)) {
        countErrors1++;
      }
    }
    System.out.println("Number of incorrect values for keyscony: " + countErrors);
    System.out.println("Number of incorrect values for keysget: " + countErrors1);

    totalErrors += countErrors;
    countErrors = 0;

    //tbds.print();

//    Check the values returned for a number of prefix values
//    Compares the returned result to the true result
    String[] searchPrefixes = {"add", "bril", "cri", "da", "enl", "lor", "mar", "perfection", "rit", "sim", "tra", "una", "ze"};
    for(String prefix: searchPrefixes){
      System.out.println("Getting values for prefix: " + prefix);
      ArrayList<String> realResult = matchPrefix(prefix, words);
      ArrayList<String> yourResult = tbds.getKeysForPrefix(prefix);
      System.out.println("Result should contain: " + realResult);
      System.out.println("   Your TBDS returned: " + yourResult);
      countErrors = countDifference(realResult, yourResult);
      totalErrors += countErrors;
      System.out.println("Error Count: " + countErrors);
    }
    System.out.println("Total Errors: " + totalErrors);
  }

  public static int countDifference(ArrayList<String> realResult, ArrayList<String> yourResult){
    int result = 0;
    for(String s: realResult){
      if(!yourResult.contains(s)){
        result++;
      }
    }

    for(Object s: yourResult){
      if(!realResult.contains((String)s)){
        result++;
      }
    }
    return result;
  }

  public static ArrayList<String> matchPrefix(String prefix, HashSet<String> words){
    ArrayList<String> result = new ArrayList<String>();

    for(String s: words){
      if(s.startsWith(prefix)){
        result.add(s);
      }
    }
    return result;
  }

  public static HashSet<String> getWords() throws FileNotFoundException {
    HashSet<String> listofStrings = new HashSet<String>();
    BufferedReader in = new BufferedReader(new FileReader("words.txt"));

    try {
      //while file is ready, add that line to a hashset
      while (in.ready()) {
        listofStrings.add(in.readLine());
      }
      in.close();
    }//close file
    catch (FileNotFoundException e) {//exit program and print error
      System.out.println("File not found!");
      System.exit(-1);
    }
    catch (IOException e) {//exit program and print error
      System.out.println("IOException! Fix me!");
      System.exit(-1);
    }
    return listofStrings;//return hashset of strings
  }
}