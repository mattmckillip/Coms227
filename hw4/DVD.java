package hw4;

/**
 * 
 * @author Matt
 * 
 */
public class DVD extends AbstractItem
{

	protected DVD(String title, String genre, int barcode)
	{
		super(title, genre, barcode);
	}

	@Override
	public void setRented(SimpleDate today) throws StatusException
	{
		// throws an exception if the item is already rented
		if (isRented())
		{
			throw new StatusException("Item is already rented");
		}
		rented = true;
		dueDate = new SimpleDate(today, 5);

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
		return 350;
	}

	@Override
	public int calculateLateFee(SimpleDate today)
	{

		// see if the DVD is overdue
		if (getDueDate().isBefore(today))
		{
			// multiply the days it is late by $1
			return getDueDate().daysUntil(today) * 100;
		}
		// DVD is not overdue
		else
		{
			return 0;
		}
	}

	@Override
	public String getGenre()
	{
		return genre;
	}

}
