package hw4;

import java.util.ArrayList;

/**
 * A Customer is a client of a VideoStore who can rent items. A client is
 * identified by a unique name. At any given time a Customer has a list of items
 * currently rented, and a balance representing rental charges, late fees, or
 * credits (where a negative balance indicates a credit). Balances are in cents.
 * Ordinary customers are not allowed to rent new items if they have any items
 * overdue.
 */
public class Customer
{
	/**
	 * Items currently rented by this customer.
	 */
	protected ArrayList<Item> itemsOut = new ArrayList<Item>();

	/**
	 * Balance currently owed by this customer.
	 */
	protected int balance;

	/**
	 * Name of this customer.
	 */
	protected String name;

	/**
	 * Constructs a Customer with the given name. Initially there are no items
	 * rented and the balance is zero.
	 * 
	 * @param name
	 *            the new customer's name
	 */
	public Customer(String name)
	{
		this.name = name;
	}

	/**
	 * Rents an item and adds it to this customer's list of items. If the item
	 * can be rented, this method updates the item's status (including the due
	 * date) and then adds it to this customer's list of items. If the item
	 * cannot be rented to this customer, a StatusException is thrown.
	 * 
	 * @param item
	 *            the item to be rented
	 * @param today
	 *            the date on which the item is being rented
	 * @throws StatusException
	 *             if the item cannot be rented to this customer for any reason
	 */
	public void rentItem(Item item, SimpleDate today) throws StatusException
	{
		if (!canRent(today))
		{
			throw new StatusException("Customer already has overdue items");
		}
		item.setRented(today);
		balance += item.getRentalCost();
		itemsOut.add(item);
	}

	/**
	 * Returns an item that this customer currently has rented and updates the
	 * balance if a late fee or credit is due. If the item can be successfully
	 * returned, this method updates the item's status and removes it from this
	 * customer's list of items. If the customer does not have the item rented,
	 * a StatusException is thrown.
	 * 
	 * @param barcode
	 *            identifier for the item to be returned
	 * @param today
	 *            the date on which the item is being returned
	 * @throws StatusException
	 *             if this customer does not have the given item rented
	 */
	public void bringBackItem(int barcode, SimpleDate today)
			throws StatusException
	{
		// looking for the item in the itemsOut arrayList
		for (int i = 0; i < itemsOut.size(); i++)
		{
			// found the item we were looking for
			if (itemsOut.get(i).getBarcode() == barcode)
			{
				// update balance with the late fees
				balance += itemsOut.get(i).calculateLateFee(today);
				// set the item as returned
				itemsOut.get(i).setReturned();
				// update costumers list of items checked out
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

	/**
	 * Returns the balance for this customer.
	 * 
	 * @return this customer's balance
	 */
	public int getBalance()
	{
		return balance;
	}

	/**
	 * Returns the name of this customer.
	 * 
	 * @return this customer's name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Makes a payment on this customer's balance.
	 * 
	 * @param amount
	 *            the amount to be paid, in cents
	 */
	public void makePayment(int amount)
	{
		balance -= amount;
	}

	/**
	 * Returns a string representation of this customer. The format consists of
	 * multiple lines. The first line is the patron's name. Subsequent lines are
	 * formed from the toString() values of the items currently rented,
	 * separated by a newline.
	 * 
	 * @return representation of this object as a multi-line string
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(name + ":");
		for (Item item : itemsOut)
		{
			sb.append(item);
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Helper method determines whether this customer already has overdue items.
	 * 
	 * @param today
	 *            the current date
	 * @return true if the customer has no overdue items, false otherwise
	 */
	protected boolean canRent(SimpleDate today)
	{
		for (Item item : itemsOut)
		{
			if (item.getDueDate().isBefore(today))
			{
				return false;
			}
		}
		return true;
	}
}
