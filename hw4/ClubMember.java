package hw4;
/**
 * 
 * @author Matt
 *
 */
public class ClubMember extends Customer
{

	public ClubMember(String name)
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
				// create a new SimpleDate which represents one day after due
				// date
				int daysLate = itemsOut.get(i).getDueDate().daysUntil(today);
				// the item is not more than one day late
				if (daysLate <= 1)
				{// check to see if there is a new release early bonus
					if (itemsOut.get(i).calculateLateFee(today) < 0)
					{
						balance += itemsOut.get(i).calculateLateFee(today);
					}
					itemsOut.get(i).setReturned();
					// update costumers list of items checked out
					itemsOut.remove(i);
				}
				// item is more than one day late, so normal late fees apply
				else
				{
					// update balance with the late fees
					balance += itemsOut.get(i).calculateLateFee(today);
					// set the item as returned
					itemsOut.get(i).setReturned();
					// update costumers list of items checked out
					itemsOut.remove(i);
					// set i to the end value to get out of the loop since the
					// item
					// has been found
					i = itemsOut.size();
				}

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
		// club members can always rent items
		return true;
	}

}
