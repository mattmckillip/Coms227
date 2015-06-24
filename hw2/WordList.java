package hw2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Class representing a dictionary of short words for 
 * word-guessing games.
 */
public class WordList
{
  /**
   * Lists of words of various lengths.
   */
  private String[] words;

  /**
   * Possible lengths of words in this list.
   */
  private ArrayList<Integer> sizes;

  /**
   * Random number generator for generating random words.
   */
  private Random rand;

  /**
   * Constructs a new WordList containing words from the given file
   * and using the given random number generator.
   * @param filename
   * @param givenRandom
   * @throws FileNotFoundException
   */
  public WordList(String filename, Random givenRandom) throws FileNotFoundException
  {
    sizes = new ArrayList<Integer>();
    rand = givenRandom;
    initialize(filename);
  }
  
  /**
   * Constructs a do-nothing word list that contains no words.
   */
  public WordList()
  {
    
  }
  
  /**
   * Returns a randomly selected word from this WordList if one
   * exists of the specified size.
   * @return
   *   a randomly selected word, or null if no word exists of
   *   the specified size
   */
  public String generate(int size)
  {
    if (rand == null || !sizes.contains(size))
    {
      return null;
    }
    int index = rand.nextInt(words.length);
    while (words[index].length() != size)
    {
      index = rand.nextInt(words.length);
    }
    return words[index];
  }

  /**
   * Determines whether the given word occurs in this word list.
   * @param word
   *   the word to check
   * @return
   *   true if the word occurs in this word list or if the word
   *   list is empty
   */
  public boolean check(String word)
  {
    if (words == null)
    {
      return true;
    }
    return binarySearch(words, word) >= 0;
  }


  /**
   * Initializes the WordList from a file of words separated
   * by whitespace.
   * @param filename
   *   name of the file containing words
   * @throws FileNotFoundException
   */
  private void initialize(String filename) throws FileNotFoundException
  {
    // if there is no filename, do nothing
    if (filename == null) return;
    
    // First read the file, and store the lengths of the words found
    File f = new File(filename);
    Scanner scanner = new Scanner(f);
    ArrayList<String> tempList = new ArrayList<String>();
    while (scanner.hasNext())
    {
      String word = scanner.next();
      
      // record the possible lengths of words in this list
      int length = word.length();
      if (!sizes.contains(length))
      {
        sizes.add(length);
      }
      
      // add the word to the list
      tempList.add(word.toLowerCase());
    }
    scanner.close();
    
    words = tempList.toArray(new String[tempList.size()]);
  }
  
  /**
   * Returns the index of a string in a sorted array, 
   * or -1 if the string is not present.
   * @param list
   *   array of strings
   * @param word
   *   the string for which to search
   * @return
   *   index of the string, or -1 if it is not found
   */
  private static int binarySearch(String[] list, String word)
  {
    int start = 0; 
    int end = list.length - 1;
    while (start <= end)
    {
      int mid = (start + end) / 2;
      int comp = word.compareTo(list[mid]);
      if (comp == 0)
      {
        return mid;
      }
      else if (comp < 0)
      {
        end = mid - 1;
      }
      else
      {
        start = mid + 1;
      }
    }
    return -1;
  }


}
