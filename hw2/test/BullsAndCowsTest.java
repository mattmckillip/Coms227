package test;

import java.io.FileNotFoundException;
import java.util.Random;

import hw2.BullsAndCowsGame;
import hw2.WordList;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test for certain aspects of the behavior of BullsAndCowsGame.
 */
public class BullsAndCowsTest {
    /**
     * Size of words to use for the test game.
     */
    public static final int SIZE = 5;

    /**
     * Do-nothing word list to use for testing.
     */
    private WordList wordList = new WordList();

    // Going through the example provided in the assignment spec
    // before first guess
    @Test
    public void testInitialSecretWord() throws FileNotFoundException {
	String msg = "After construction, getSecretWord() should return the given string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	assertEquals(msg, "aisle", game.getSecretWord());
    }

    @Test
    public void testInitialGuesses() throws FileNotFoundException {
	String msg = "If no words have been guessed, getAllLettersGuessed should return an empty string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	assertEquals(msg, "", game.getAllGuessedLetters());
    }

    @Test
    public void testInitialBulls() throws FileNotFoundException {
	String msg = "If no words have been guessed, getBulls should return only PLACEHOLDERS";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	assertEquals(msg, "*****", game.getBulls());
    }

    @Test
    public void testInitialGeese() throws FileNotFoundException {
	String msg = "If no words have been guessed, getGeese should return an empty string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	assertEquals(msg, "", game.getGeese());
    }

    @Test
    public void testInitialCows() throws FileNotFoundException {
	String msg = "If no words have been guessed, getCows should return an empty string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	assertEquals(msg, "", game.getCows());

    }

    @Test
    public void testInitialisOver() throws FileNotFoundException {
	String msg = "If no words have been guessed, getOver should return false";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	assertEquals(msg, false, game.isOver());

    }

    // after first guess
    @Test
    public void testAfterFirstGuessSecretWord() throws FileNotFoundException {
	String msg = "After the first guess, getSecretWord() should still return the given string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	assertEquals(msg, "aisle", game.getSecretWord());
    }

    @Test
    public void testAfterFirstGuessGuesses() throws FileNotFoundException {
	String msg = "After house has been guessed, getAllLettersGuessed should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	assertEquals(msg, "house", game.getAllGuessedLetters());
    }

    @Test
    public void testAfterFirstGuessBulls() throws FileNotFoundException {
	String msg = "After house has been guessed, getBulls should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	assertEquals(msg, "****e", game.getBulls());
    }

    @Test
    public void testAfterFirstGuessGeese() throws FileNotFoundException {
	String msg = "After house has been guessed, getGeese should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	assertEquals(msg, "hou", game.getGeese());
    }

    @Test
    public void testAfterFirstGuessCows() throws FileNotFoundException {
	String msg = "After house has been guessed, getCows should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	assertEquals(msg, "s", game.getCows());
    }

    // after second guess
    @Test
    public void testAfterSecondGuessisOver() throws FileNotFoundException {
	String msg = "If the secret word hasn't been guessed, getOver should return false";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	game.guess("trite");
	assertEquals(msg, false, game.isOver());
    }

    @Test
    public void testAfterSecondKeepTurnStatus() throws FileNotFoundException {
	String msg = "If the players guess does not contains a bull, the status be LOSE_TURN";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	assertEquals(msg, game.guess("trite"), Status.LOSE_TURN);
    }

    @Test
    public void testAfterSecondGuessGuesses() throws FileNotFoundException {
	String msg = "After the 'trite' is guessed, getAllLettersGuessed should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	game.guess("trite");
	assertEquals(msg, "housetri", game.getAllGuessedLetters());
    }

    @Test
    public void testAfterSecondGuessBulls() throws FileNotFoundException {
	String msg = "After house has been guessed, getBulls should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	game.guess("trite");
	assertEquals(msg, "****e", game.getBulls());
    }

    @Test
    public void testAfterSecondGuessGeese() throws FileNotFoundException {
	String msg = "After house has been guessed, getGeese should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	game.guess("trite");
	assertEquals(msg, "houtr", game.getGeese());
    }

    @Test
    public void testAfterSecondGuessCows() throws FileNotFoundException {
	String msg = "After trite, getCows should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	game.guess("trite");
	assertEquals(msg, "si", game.getCows());
    }

    // testing the interesting portions of the rest of the example
    @Test
    public void testCowtoBull() throws FileNotFoundException {
	String msg = "When the player guesses the bull that used to be a cow , getCows should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	game.guess("trite");
	game.guess("smile");
	game.guess("sidle");
	assertEquals(msg, "s", game.getCows());
    }

    @Test
    public void testCowtoBullTwo() throws FileNotFoundException {
	String msg = "When the player guesses the bull that used to be a cow , getCows should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	game.guess("trite");
	game.guess("smile");
	game.guess("sidle");
	game.guess("nasty");
	assertEquals(msg, "a", game.getCows());
    }

    @Test
    public void testCowtoBullThree() throws FileNotFoundException {
	String msg = "When the player guesses the bull that used to be a cow , getBulls should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	game.guess("trite");
	game.guess("smile");
	game.guess("sidle");
	game.guess("nasty");
	assertEquals(msg, "*isle", game.getBulls());
    }

    @Test
    public void testExampleWin() throws FileNotFoundException {
	String msg = "When the player guesses the secret word , the status should be WIN";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	game.guess("trite");
	game.guess("smile");
	game.guess("sidle");
	game.guess("nasty");
	assertEquals(msg, Status.WIN, game.guess("aisle"));
    }

    @Test
    public void testExampleWinIsOver() throws FileNotFoundException {
	String msg = "When the player guesses the secret word , isOver should return true";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("house");
	game.guess("trite");
	game.guess("smile");
	game.guess("sidle");
	game.guess("nasty");
	game.guess("aisle");
	assertEquals(msg, true, game.isOver());
    }

    // Other interesting situations that may cause problems
    // capital letters
    @Test
    public void testCapitalInputBulls() throws FileNotFoundException {
	String msg = "When the player inputs capital letters, the bulls should be the same as lowercase, getBulls should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("HOUSE");
	assertEquals(msg, "****e", game.getBulls());
    }

    @Test
    public void testCapitalInputCows() throws FileNotFoundException {
	String msg = "When the player inputs capital letters, the cows should be the same as lowercase, getCows should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("HOUSE");
	assertEquals(msg, "s", game.getCows());
    }

    @Test
    public void testCapitalInputGeese() throws FileNotFoundException {
	String msg = "When the player inputs capital letters, the geese should be the same as lowercase, getGeese should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("HOUSE");
	assertEquals(msg, "hou", game.getGeese());
    }

    @Test
    public void testCapitalInputGetAllGuessedLetters()
	    throws FileNotFoundException {
	String msg = "When the player inputs capital letters, the guessedLetters should all be lowercase, getAllGuessedLetters should return the following string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("HOUSE");
	assertEquals(msg, "house", game.getAllGuessedLetters());
    }

    // wrong word size
    @Test
    public void testWrongSizeLongStatus() throws FileNotFoundException {
	String msg = "When the player inputs a string that is too long, the status should be INVALID_WORD";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	assertEquals(msg, Status.INVALID_WORD, game.guess("housee"));
    }

    @Test
    public void testWrongSizeLongGuessedLetters() throws FileNotFoundException {
	String msg = "When the player inputs a string that is too long, getAllGuessedLetters should return an empty string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("housee");
	assertEquals(msg, "", game.getAllGuessedLetters());
    }

    @Test
    public void testWrongSizeLongBulls() throws FileNotFoundException {
	String msg = "When the player inputs a string that is too long, getBulls should not be updated";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("housee");
	assertEquals(msg, "*****", game.getBulls());
    }

    @Test
    public void testWrongSizeShortStatus() throws FileNotFoundException {
	String msg = "When the player inputs a string that is too long, the status should be INVALID_WORD";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	assertEquals(msg, Status.INVALID_WORD, game.guess("ouse"));
    }

    @Test
    public void testWrongSizeShortGuessedLetters() throws FileNotFoundException {
	String msg = "When the player inputs a string that is too short, getAllGuessedLetters should return an empty string";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("ouse");
	assertEquals(msg, "", game.getAllGuessedLetters());
    }

    @Test
    public void testWrongSizeShortBulls() throws FileNotFoundException {
	String msg = "When the player inputs a string that is too short, getBulls should not be updated";
	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "aisle");
	game.guess("ouse");
	assertEquals(msg, "*****", game.getBulls());
    }

    // guess is not in the dictionary
    @Test
    public void testNotInDictionaryStatus() throws FileNotFoundException {
	String msg = "When the player inputs a string that is not in the dictionary, game.Status should be INVALID WORD";
	Random rand = new Random(42);
	WordList list = new WordList("C:/Users/Matt/workspace/ComS221/src/words.txt", rand);
	BullsAndCowsGame game = new BullsAndCowsGame(list, SIZE);
	assertEquals(msg, Status.INVALID_WORD, game.guess("xzxzx"));
    }
    @Test
    public void testNotInDictionaryBulls() throws FileNotFoundException {
   	String msg = "When the player inputs a string that is not in the dictionary, the bulls string should not be updated";
   	Random rand = new Random(42);
   	WordList list = new WordList("C:/Users/Matt/workspace/ComS221/src/words.txt", rand);
   	BullsAndCowsGame game = new BullsAndCowsGame(list, SIZE);
   	game.guess("xzxzx");
   	assertEquals(msg, "*****", game.getBulls());
       }
    @Test
    public void testNotInDictionaryGuessedLetters() throws FileNotFoundException {
   	String msg = "When the player inputs a string that is not in the dictionary, the allGuessedLetters String should not be updated";
   	Random rand = new Random(42);
   	WordList list = new WordList("C:/Users/Matt/workspace/ComS221/src/words.txt", rand);
   	BullsAndCowsGame game = new BullsAndCowsGame(list, SIZE);
   	game.guess("xzxzx");
   	assertEquals(msg, "", game.getAllGuessedLetters());
       }
    //words with double letters
    @Test
    public void testDoubleLettersSecretWordBulls() throws FileNotFoundException {
  	String msg = "When there is a double letter in the secret word and the player guesses one correctly, getBulls should return";
  	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "hunch");
  	game.guess("husky");
  	assertEquals(msg, "hu***", game.getBulls());
      }
    @Test
    public void testDoubleLettersSecretWordCows() throws FileNotFoundException {
  	String msg = "When there is a double letter in the secret word and the player guesses one correctly, getBulls should return";
  	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "hunch");
  	game.guess("husky");
  	assertEquals(msg, "h", game.getCows());
      }
    @Test
    public void testDoubleLettersSecretWordBulls2() throws FileNotFoundException {
  	String msg = "When there is a double letter in the secret word and the player guesses both correctly, getBulls should return";
  	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "hunch");
  	game.guess("hatch");
  	assertEquals(msg, "h**ch", game.getBulls());
      }
    
    @Test
    public void testDoubleLettersSecretWordCows2() throws FileNotFoundException {
  	String msg = "When the secret word is levee and the player guesses event, getCows should return";
  	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "levee");
  	game.guess("event");
  	assertEquals(msg, "eeev", game.getCows());
      }
    @Test
    public void testDoubleLettersSecretWordGeese() throws FileNotFoundException {
  	String msg = "When the secret word is levee and the player guesses event, getGeese should return";
  	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "levee");
  	game.guess("event");
  	assertEquals(msg, "nt", game.getGeese());
      }
    @Test
    public void testDoubleLettersSecretWordCows3() throws FileNotFoundException {
  	String msg = "When the secret word is fleet and the player guesses great, getCows should return";
  	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "fleet");
  	game.guess("great");
  	assertEquals(msg, "e", game.getCows());
      }
    @Test
    public void testDoubleLettersSecretWordBulls3() throws FileNotFoundException {
  	String msg = "When the secret word is fleet and the player guesses great, getBulls should return";
  	BullsAndCowsGame game = new BullsAndCowsGame(wordList, "fleet");
  	game.guess("great");
  	assertEquals(msg, "**e*t", game.getBulls());
      }
    //start new round
    @Test
    public void testStartNewRoundInitialAllGuessedLetters() throws FileNotFoundException {
  	String msg = "When startNewRound is called, allGuessedLetters should return an empty string";
  	Random rand = new Random(42);
	WordList list = new WordList("C:/Users/Matt/workspace/ComS221/src/words.txt", rand);
  	BullsAndCowsGame game = new BullsAndCowsGame(list, "fleet");
  	game.guess("great");
  	game.startNewRound();
  	assertEquals(msg, "", game.getAllGuessedLetters());
      }
    @Test
    public void testStartNewRoundInitialBulls() throws FileNotFoundException {
  	String msg = "When startNewRound is called, getBulls should return an empty string";
  	Random rand = new Random(42);
	WordList list = new WordList("C:/Users/Matt/workspace/ComS221/src/words.txt", rand);
  	BullsAndCowsGame game = new BullsAndCowsGame(list, "fleet");
  	game.guess("great");
  	game.startNewRound();
  	assertEquals(msg, "*****", game.getBulls());
      }
    @Test
    public void testStartNewRoundInitialCows() throws FileNotFoundException {
  	String msg = "When startNewRound is called, getCows should return an empty string";
  	Random rand = new Random(42);
	WordList list = new WordList("C:/Users/Matt/workspace/ComS221/src/words.txt", rand);
  	BullsAndCowsGame game = new BullsAndCowsGame(list, SIZE);
  	game.guess("great");
  	game.startNewRound();
  	assertEquals(msg, "", game.getCows());
      }
    @Test
    public void testStartNewRoundInitialGeese() throws FileNotFoundException {
  	String msg = "When startNewRound is called, getGeese should return an empty string";
  	Random rand = new Random(42);
	WordList list = new WordList("C:/Users/Matt/workspace/ComS221/src/words.txt", rand);
  	BullsAndCowsGame game = new BullsAndCowsGame(list, SIZE);
  	game.guess("great");
  	game.startNewRound();
  	assertEquals(msg, "", game.getGeese());
      }
  
}
