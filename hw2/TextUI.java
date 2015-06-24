package hw2;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * A simple text-based user interface for the BullsAndCowsGame.
 */
public class TextUI {
    /**
     * Name of file containing words separated by whitespace.
     */
    public static final String DICTIONARY_FILENAME = "C:\\Users\\Matt\\workspace\\ComS221\\src\\words.txt";

    /**
     * Size of words to use for this game.
     */
    public static final int WORD_SIZE = 5;

    /**
     * The game object used by this UI.
     */
    private BullsAndCowsGame game;

    /**
     * Scanner for user input.
     */
    private Scanner scanner;

    /**
     * Entry point.
     */
    public static void main(String[] args) throws FileNotFoundException {
	Random rand = new Random(37);
	WordList list = new WordList(DICTIONARY_FILENAME, rand);
	BullsAndCowsGame game = new BullsAndCowsGame(list, WORD_SIZE);
	TextUI ui = new TextUI(game);
	ui.runUI();
    }

    /**
     * Constructs a user interface that will use the given game.
     * 
     * @param givenGame
     */
    public TextUI(BullsAndCowsGame givenGame) {
	game = givenGame;
	scanner = new Scanner(System.in);
    }

    /**
     * Main user interface loop.
     */
    public void runUI() {
	boolean done = false;

	// start with player 0
	int whoseTurn = 0;

	while (!done) {
	    display();
	    System.out.print("Player " + whoseTurn + ", enter your guess: ");
	    String word = scanner.next().trim().toLowerCase();
	    Status status = game.guess(word);
	    if (status == Status.INVALID_WORD) {
		System.out.println("Invalid word!");
		whoseTurn = 1 - whoseTurn;
		System.out
			.println("It is player " + whoseTurn + "'s turn now.");
	    } else if (status == Status.LOSE_TURN) {
		System.out.println("Sorry, no new bulls.");
		whoseTurn = 1 - whoseTurn;
		System.out
			.println("It is player " + whoseTurn + "'s turn now.");
	    } else if (status == Status.KEEP_TURN) {
		System.out.println("Good guess!");
	    } else if (status == Status.WIN) {
		System.out.println("You won!");
		System.out.println("The word was " + game.getSecretWord());
		done = !playAgain();
		if (!done) {
		    game.startNewRound();
		    whoseTurn = 1 - whoseTurn;
		}
	    }
	}
    }

    /**
     * Displays the game state.
     */
    private void display() {
	System.out.println();
	System.out.println("----------------------------");
	System.out.println("Bulls: " + game.getBulls());
	System.out.println("Cows:  " + game.getCows());
	System.out.println("Geese: " + game.getGeese());
	System.out.println("----------------------------");
    }

    /**
     * Queries whether the player wants to play again.
     * 
     * @return true if the player chooses to play again, false otherwise
     */
    private boolean playAgain() {
	System.out.print("Do you want to play again (y/n)? ");
	String response = scanner.next();
	return response.toLowerCase().startsWith("y");
    }
}
