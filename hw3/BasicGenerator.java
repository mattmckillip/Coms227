package hw3;

import java.util.Random;

import hw3.api.IGenerator;
import hw3.api.Icon;

public class BasicGenerator implements IGenerator
{
	/**
	 * the instance of Random() used in the generate()
	 */
	private Random random;
	/**
	 * the number of icon types there will be in this game
	 */
	private int numberOfTypes;

	/**
	 * Constructs a BasicGenerator that will create icons with types 0 through
	 * numTypes - 1.
	 * 
	 * @param numTypes
	 *            the number of icon types that will be used in this game
	 */
	public BasicGenerator(int numTypes)
	{
		numberOfTypes = numTypes;
		// create a new instance of random since there is not one passed into
		// this constructor
		Random rand = new Random();
		random = rand;
	}

	/**
	 * Constructs a BasicGenerator that will create icons with types 0 through
	 * numTypes - 1, using the given Random instance.
	 * 
	 * @param numTypes
	 *            the number of icon types that will be used in this game
	 * @param rand
	 *            instance of random passed into the game
	 */
	public BasicGenerator(int numTypes, java.util.Random rand)
	{
		random = rand;
		numberOfTypes = numTypes;
	}

	@Override
	public Icon generate()
	{
		// create a new random int using either the instance of Random passed in
		// or created
		int randomInt = random.nextInt(numberOfTypes);
		// randomly create a new icon
		Icon icon = new BasicIcon(randomInt);
		return icon;
	}

	@Override
	public void initialize(Icon[][] grid)
	{
		// when true, the icons adjacent icons are not the same
		boolean noAdjacentIcons = false;
		// generate the first icon for reference
		grid[0][0] = generate();
		// generate the first row since the algorithm will check left of the
		// cell and above the cell
		for (int c = 1; c < grid[0].length; c++)
		{
			// set boolean to false
			noAdjacentIcons = false;
			while (!noAdjacentIcons)
			{
				// generate a random icon
				Icon icon = generate();
				// if the icon does not equal the icon to the left
				if (!icon.equals(grid[0][c - 1]))
				{
					// initialize icon
					grid[0][c] = icon;
					noAdjacentIcons = true;
				}
			}
			// Generate first column
			for (int r = 1; r < grid.length; r++)
			{
				// set boolean to false
				noAdjacentIcons = false;
				while (!noAdjacentIcons)
				{
					// generate a random icon
					Icon icon = generate();
					// if the icon does not equal the icon to the left
					if (!icon.equals(grid[r - 1][0]))
					{
						// initialize icon
						grid[r][0] = icon;
						noAdjacentIcons = true;
					}
				}

				// generate the rest of the rows
				for (int row = 1; row < grid.length; row++)
				{

					for (int col = 1; col < grid[0].length; col++)
					{
						noAdjacentIcons = false;
						while (!noAdjacentIcons)
						{
							// generate a random icon
							Icon icon = generate();
							// if the icon does not equal the icon to the left
							if (!icon.equals(grid[row][col - 1])
									&& !icon.equals(grid[row - 1][col]))
							{
								// initialize icon
								grid[row][col] = icon;
								noAdjacentIcons = true;
							}
						}
					}
				}
			}
		}
	}
}
