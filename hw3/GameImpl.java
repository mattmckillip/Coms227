package hw3;

import hw3.api.Cell;
import hw3.api.IGame;
import hw3.api.IGenerator;
import hw3.api.Icon;

import java.util.ArrayList;
import java.util.Random;

/**
 * Concrete implementation of the IGame interface. This implementation has the
 * following behavior:
 * <ul>
 * <li>Moves are allowed via the select() method for pairs of adjacent cells
 * only. The cells must have different types. The move must create at least one
 * run.
 * <li>A run is defined to be three or more adjacent cells of the same type,
 * horizontally or vertically. A given cell may be part of a horizontal run or a
 * vertical run at the same time.
 * <li>Points are awarded for runs based on their length. A run of length 3 is
 * awarded BASE_SCORE points, and a run of length (3 + n) points gets BASE_SCORE
 * times 2 to the power n.
 * </ul>
 */
public class GameImpl implements IGame
{
	/**
	 * Score awarded for a three-cell run.
	 */
	private static final int BASE_SCORE = 10;

	/**
	 * The grid of icons for this game.
	 */
	private Icon[][] grid;

	/**
	 * Integer representing the score of the current game.
	 */
	private int score;
	/**
	 * The given instance of IGenerator given in the constructor
	 */
	private IGenerator gen;

	/**
	 * Constructs a game with the given number of columns and rows that will use
	 * the given <code>IGenerator</code> instance to create new icons.
	 * 
	 * @param width
	 *            number of columns
	 * @param height
	 *            number of rows
	 * @param generator
	 *            generator for new icons
	 */
	public GameImpl(int width, int height, IGenerator generator)
	{
		// Create and initialize the grid.
		grid = new Icon[height][width];
		generator.initialize(grid);
		// initialize score to zero
		score = 0;
		// save to instance variable to be able to call generate()
		gen = generator;
	}

	@Override
	public Icon getIcon(int row, int col)
	{
		return grid[row][col];
	}

	@Override
	public void setIcon(int row, int col, Icon icon)
	{
		grid[row][col] = icon;
	}

	@Override
	public int getWidth()
	{
		return grid[0].length;
	}

	@Override
	public int getHeight()
	{
		return grid.length;
	}

	@Override
	public int getScore()
	{
		return score;
	}

	/**
	 * {@inheritDoc} In this implementation, the only possible move is a swap of
	 * two adjacent cells. In order for move to be made, the following must be
	 * true.
	 * <ul>
	 * <li>The given array has length 2
	 * <li>The two given cell positions must be adjacent
	 * <li>The two given cell positions must have different icon types
	 * <li>Swapping the two icons must result in at least one run.
	 * </ul>
	 * If the conditions above are satisfied, the icons for the two positions
	 * are exchanged and the method returns true; otherwise, the method returns
	 * false. No other aspects of the game state are modified.
	 */
	@Override
	public boolean select(Cell[] cells)
	{
		// given array has length of 2
		if (cells.length == 2)
		{
			// if the absolute value of the difference is 1 or less (0) then the
			// rows and columns are adjacent
			int row1 = cells[0].row();
			int row2 = cells[1].row();
			int col1 = cells[0].col();
			int col2 = cells[1].col();
			if (Math.abs(row1 - row2) <= 1 && (Math.abs(col1 - col2) <= 1))
			{
				// the icons are not the same
				if (!cells[0].getIcon().equals(cells[1].getIcon()))
				{
					// update grid[][] to check for runs

					grid[row1][col1] = cells[1].getIcon();
					grid[row2][col2] = cells[0].getIcon();

					// the arraylist returned from findRuns() contains a run of
					// three or more
					if (findRuns(false).size() > 1)
					{
						return true;
					} else
					{
						// restore grid back to previous state
						// grid = tempGrid;
						setIcon(row1, col1, cells[0].getIcon());
						setIcon(row2, col2, cells[1].getIcon());
					}
				}
			}
		}
		return false;
	}

	@Override
	public ArrayList<Cell> findRuns(boolean doMarkAndUpdateScore)
	{
		int tempScore = 0;
		Icon compareIcon;
		int count = 1;
		ArrayList<Cell> cells = new ArrayList<Cell>();
		// Find horizontal runs
		for (int row = 0; row < getHeight(); row++)
		{
			// initialize the count and the icon to compare too
			compareIcon = getIcon(row, 0);
			count = 1;
			// loop through the different columns of the row
			for (int col = 1; col < getWidth(); col++)
			{
				// if the icon is the same as the previous icon add one to the
				// count
				if (getIcon(row, col).equals(compareIcon))
				{
					count += 1;
				}
				// otherwise, check to see if a run just ended
				else
				{
					// if it was a run
					if (count >= 3)
					{
						// add to the temporary score
						tempScore += BASE_SCORE * Math.pow(2, count - 3);

						// change the index to where the run started
						int index = col - count;
						while (index < col)
						{
							// add the cells to the ArrayList to output
							Cell c = new Cell(row, index, getIcon(row, index));
							cells.add(c);
							index += 1;
						}
					}
					// update the icon to compare too
					compareIcon = getIcon(row, col);
					count = 1;
				}
			}
			// if the run is at the end of the row
			if (count >= 3)
			{
				// update score
				tempScore += BASE_SCORE * Math.pow(2, count - 3);
				// set the index to where the run started
				int index = getWidth() - count;
				while (index < getWidth())
				{
					// add the cells to the ArrayList to output
					Cell c = new Cell(row, index, getIcon(row, index));
					cells.add(c);
					index += 1;
				}
			}
		}

		// Find the verticle runs
		for (int col = 0; col < getWidth(); col++)
		{
			// initialize counter and compareIcon
			compareIcon = getIcon(0, col);
			count = 1;
			// loop through the column
			for (int row = 1; row < getWidth(); row++)
			{
				// if the icon equals the previous one add to the count
				if (getIcon(row, col).equals(compareIcon))
				{
					count += 1;
				}
				// otherwise, check to see if that was a run
				else
				{
					// it is a run
					if (count >= 3)
					{
						// update score and index
						tempScore += BASE_SCORE * Math.pow(2, count - 3);
						int index = row - count;
						while (index < row)
						{
							// add cells to the ArrayList
							Cell c = new Cell(index, col, getIcon(index, col));
							cells.add(c);
							index += 1;
						}
					}
					// change the counter and compareIcon
					compareIcon = getIcon(row, col);
					count = 1;
				}
			}
			// Did it end on a run?
			if (count >= 3)
			{
				// It did, so update the score and the index
				int exp = count - 3;
				tempScore += BASE_SCORE * Math.pow(2, count - 3);
				int index = getHeight() - count;
				while (index < getHeight())
				{
					// Add the cells to the ArrayList to output
					Cell c = new Cell(index, col, getIcon(index, col));
					cells.add(c);
					index += 1;
				}
			}
		}
		// if we want to update the grid with null icons and update the score
		if (doMarkAndUpdateScore)
		{
			Icon nullIcon = null;
			// for every cell listed in the ArrayList, change the corresponding
			// icon to null
			for (int i = 0; i < cells.size(); i++)
			{
				setIcon(cells.get(i).row(), cells.get(i).col(), nullIcon);

			}
			// update the score
			score += tempScore;
		}
		// return the ArrayList of cells
		return cells;
	}

	@Override
	public ArrayList<Cell> collapseColumn(int col)
	{
		// Create new ArrayList that will be used to output the final position
		// of the cells
		ArrayList<Cell> cells = new ArrayList<Cell>();
		// collapse grid column down
		for (int i = grid.length - 1; i >= 0; i--)
		{
			// If the icon is a null icon
			if (grid[i][col] == null)
			{
				// if the icon is not at the top and if the icon above it is not
				// null
				if (i != 0 && grid[i - 1][col] != null)
				{
					// shift the icon from above to the current position
					grid[i][col] = grid[i - 1][col];
					// set the position above to be null, which essentially
					// switches the icons
					grid[i - 1][col] = null;
					// add this icon to the cell array that is returned
					Cell c = new Cell(i, col, grid[i][col]);
					// set the previous row to i-1
					c.setPreviousRow(i - 1);
					//add the cell to the array
					cells.add(c);
				} else
				{
					// if the icon above was null, check until you get to the
					// top to see if there are any non null icons
					int j = i - 1;
					while (j >= 0 && grid[j][col] == null)
					{
						if (j == 0)
						{
							// got to the top of the column and did not find any
							// more non null icons
							break;
						}
						// still more icons to check above
						else
						{
							j -= 1;
						}
					}
					// if the icon at the index j isnt null
					if (j >= 0 && grid[j][col] != null)
					{
						// swap the icons
						grid[i][col] = grid[j][col];
						grid[j][col] = null;
						// add to the ArrayList of cells
						Cell c = new Cell(i, col, grid[i][col]);
						// set the prevoius row to j
						c.setPreviousRow(j);
						//add the cell to the array
						cells.add(c);
					}

				}
			}
		}
		// return the ArrayList of Cells that have moved
		return cells;
	}

	@Override
	public ArrayList<Cell> fillColumn(int col)
	{
		// Create the ArrayList that will be returned
		ArrayList<Cell> cells = new ArrayList<Cell>();
		// Starting at the bottom of the column and iterating to the top
		int i = 0;
		//starting from the top, fill in null icons until it reaches a spot with an icon
		while (grid[i][col] == null)
		{
			// generate a new icon and set the icon to the location
			setIcon(i, col, gen.generate());
			// add this new cell to the ArrayList<Cell>
			Cell newCell = new Cell(i, col, getIcon(i, col));
			cells.add(newCell);
			i += 1;
		}
		return cells;
	}

	/**
	 * Returns a String representation of the grid for this game, with rows
	 * delimited by newlines.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < grid.length; ++row)
		{
			for (int col = 0; col < grid[0].length; ++col)
			{
				Icon icon = grid[row][col];
				String s = String.format("%3s",
						(icon == null ? "*" : "" + icon.getType()));
				sb.append(s);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}