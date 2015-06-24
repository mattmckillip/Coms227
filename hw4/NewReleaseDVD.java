package hw4;

/**
 * 
 * @author Matt
 * 
 */
public class NewReleaseDVD extends DVD
{

	protected NewReleaseDVD(String title, String genre, int barcode)
	{
		super(title, genre, barcode);
	}

	@Override
	public void setRented(SimpleDate today) throws StatusException
	{
		// throw an exception if this item is already rented
		if (isRented())
		{
			throw new StatusException("Item is already rented");
		}
		rented = true;
		// set the due date to two days from today
		dueDate = new SimpleDate(today, 2);
	}

	@Override
	public int getRentalCost()
	{
		// costs 400
		return 400;
	}

	@Override
	public int calculateLateFee(SimpleDate today)
	{
		// see if the NewReleaseDVD is overdue
		if (dueDate.isBefore(today))
		{
			// if it is only one day late charge $4
			if (getDueDate().daysUntil(today) == 1)
			{
				return 400;
			}
			// it is more than 1 day late, charge an addition 150 for each day
			// late
			else
			{
				return 400 + 150 * (getDueDate().daysUntil(today) - 1);
			}
		}
		// NewReleaseDVD is returned early
		else if (getDueDate().daysUntil(today) < 0)
		{
			return -100;
		}
		// NewReleaseDVD is returned on the due date
		else
		{
			return 0;
		}
	}
}
