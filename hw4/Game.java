package hw4;

/**
 * 
 * @author Matt
 * 
 */
public class Game extends AbstractItem
{
	protected Game(String title, int barcode)
	{
		super(title, barcode);
	}

	@Override
	public void setRented(SimpleDate today) throws StatusException
	{
		// throws exception if the item is already rented
		if (isRented())
		{
			throw new StatusException("Item is already rented");
		}
		rented = true;
		// sets the due date to a week from today
		dueDate = new SimpleDate(today, 7);
	}

	@Override
	public void setReturned() throws StatusException
	{
		// throws an exception if the item has not been rented
		if (!isRented())
		{
			throw new StatusException("Item has not been rented");
		}
		rented = false;
	}

	@Override
	public int getRentalCost()
	{
		// costs 500
		return 500;
	}

	@Override
	public int calculateLateFee(SimpleDate today)
	{
		// see if the game is overdue
		if (!today.isBefore(dueDate))
		{
			// multiply the parts of a week late by $5
			return (int) (Math.ceil(dueDate.daysUntil(today) / 7.0) * 500);
		}
		// game is not overdue
		else
		{
			return 0;
		}
	}

	@Override
	public String getGenre()
	{
		// all games have the same genre
		return "GAME";
	}

}
