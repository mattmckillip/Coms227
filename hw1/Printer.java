package hw1;
/**
 * ComS 227 HW1
 * @author Matt McKillip
 *
 */
public class Printer {

	/**
	 *    * Capacity, in ounces, of a new ink cartridge.    
	 */
	public static final double INK_CAPACITY = 2.0;

	/**
	 * Amount of ink, in ounces, used per printed page.
	 */
	public static final double INK_USAGE = 0.0023;

	/**
	 * Number of sheets, in sheets, that are currently in the printer.
	 */
	private int currentNumberofSheets;

	/**
	 * Number of sheets, in sheets, that the printer started with.
	 */
	private int startingNumberOfSheets;

	/**
	 * Amount of ink, in ounces, left in the ink cartridge.
	 */
	private double inkLeft;

	/**
	 * Amount of sheets, in sheets, of ink being printed.
	 */
	private int pagesOfInkPrinted;

	/**
	 * Program replicating a printer
	 * 
	 * @param givenNumberOfSheets
	 *            number of sheets the printer is given initially.
	 */
	public Printer(int givenNumberOfSheets) {
		startingNumberOfSheets = givenNumberOfSheets;
		currentNumberofSheets = givenNumberOfSheets;
		replaceInk();
	}

	/**
	 * Adds paper to the printer.
	 * 
	 * @param additionalSheets
	 *            Additional sheets added to the printer.
	 */
	public void addPaper(int additionalSheets) {
		currentNumberofSheets = currentNumberofSheets + additionalSheets;
	}

	/**
	 * Gets the current amount of paper in the printer.
	 * 
	 * @return the amount of paper in the printer.
	 */
	public int getCurrentPaper() {
		return currentNumberofSheets;
	}

	/**
	 * Gets the total amount of paper the printer has used.
	 * 
	 * @return the amount of paper the printer has used.
	 */
	public int getTotalPaperUse() {
		return startingNumberOfSheets - currentNumberofSheets;
	}

	/**
	 * Determines if the ink has run out.
	 * 
	 * @return if the ink is out.
	 */
	public boolean isInkOut() {
		return inkLeft < 0.0023;
	}

	/**
	 * Replaces the ink to full capacity.
	 */
	public void replaceInk() {
		inkLeft = INK_CAPACITY;
	}

	/**
	 * Prints single sided sheets of paper.
	 * 
	 * @param numberOfPages
	 *            Number of pages the user wants to print.
	 */
	public void print(int numberOfPages) {

		// How many pages of ink are left.
		int pagesOfInkLeft = (int) (inkLeft / INK_USAGE);

		// Checks if there is enough paper to print all of the pages.
		int numberOfPagesToPrint = Math.min(numberOfPages,
				currentNumberofSheets);

		// checks to see if the pages of ink is limiting the number of pages to
		// be printed.
		int pagesPrinted = Math.min(numberOfPagesToPrint, pagesOfInkLeft);

		// updating the currentNumberofSheets value.
		currentNumberofSheets = currentNumberofSheets - pagesPrinted;

		// updating the inkLeft value.
		inkLeft = inkLeft - (pagesPrinted * INK_USAGE);
	}

	/**
	 * Prints double sided sheets of paper
	 * 
	 * @param numberOfPages
	 *            Number of pages the user wants to print.
	 */
	public void printTwoSided(int numberOfPages) {

		// Checks to see if there is enough ink for the numberOfPages requested.
		int pagesOfInk = Math.min(numberOfPages,
				(int) Math.floor(inkLeft / INK_USAGE));

		// Checks if there is enough paper to print numberOfPages
		int pagesPrinted = Math.min(currentNumberofSheets,
				((numberOfPages / 2) + (numberOfPages % 2)));

		// multiplies the pages printed by two since the ink will go on both
		// sides.
		int twoSidedPrinted = pagesPrinted * 2;

		// Checks if there is enough ink to print on two sided amount of ink.
		pagesOfInkPrinted = Math.min(twoSidedPrinted, pagesOfInk);

		// updating the currentNumberofSheets value.
		currentNumberofSheets -= pagesPrinted;

		// updating the inkLeft value.
		inkLeft = inkLeft - (pagesOfInkPrinted * INK_USAGE);
	}

}
