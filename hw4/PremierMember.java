package hw4;
/**
 * 
 * @author Matt
 *
 */
public class PremierMember extends Customer
{
	private int bonusPoints = 0;

	public PremierMember(String name)
	{
		super(name);
	}

	@Override
	public void bringBackItem(int barcode, SimpleDate today)
			throws StatusException
	{
		// looking for the item in the itemsOut arrayList
		for (int i = 0; i < itemsOut.size(); i++)
		{
			// found the item we were looking for
			if (itemsOut.get(i).getBarcode() == barcode)
			{
				//find the days late to see if they receieve an early return bonus
				int daysLate = itemsOut.get(i).getDueDate().daysUntil(today);
				// check to see if the received an early return bonus
				if (daysLate<=0)
				{
					// check to see if there is a new release early bonus
					if (itemsOut.get(i).calculateLateFee(today) < 0)
					{
						balance += itemsOut.get(i).calculateLateFee(today);
					}
					// give the premier customer bonus points for returning item
					bonusPoints += 3;
				}
				// return item and remove it from the costumers list
				itemsOut.get(i).setReturned();
				itemsOut.remove(i);
				// set i to the end value to get out of the loop since the item
				// has been found
				i = itemsOut.size();
			}

			// could not find item in the ArrayList
			else if (i == itemsOut.size() - 1)
			{
				throw new StatusException(
						"The costumer does not have this item");
			}
		}
	}

	@Override
	protected boolean canRent(SimpleDate today)
	{
		// premier members can always rent items
		return true;
	}

	@Override
	public int getBalance()
	{
		if (bonusPoints > 10)
		{
			// subtract the bonus points being cashed in
			bonusPoints -= 10;
			return balance -= 150;
		} else
		{
			return balance;
		}
	}
}
