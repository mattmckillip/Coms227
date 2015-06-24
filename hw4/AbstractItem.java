package hw4;

/**
 * Partial implementation of the Item interface.
 */
public abstract class AbstractItem implements Item
{
	/**
	 * Title of this item.
	 */
	protected String title;

	/**
	 * Genre of this item.
	 */
	protected String genre;

	/**
	 * Due date for this item.
	 */
	protected SimpleDate dueDate;

	/**
	 * Rental status of this item.
	 */
	protected boolean rented;

	/**
	 * Barcode for this item.
	 */
	protected int barcode;

	/**
	 * Constructs a new Item with the given title, genre, and barcode. This
	 * constructor may only be invoked by subclasses.
	 * 
	 * @param title
	 *            the title of the item
	 * @param genre
	 *            the genre of the item
	 * @param barcode
	 *            a unique integer identifier for the item
	 */
	protected AbstractItem(String title, String genre, int barcode)
	{
		this.title = title;
		this.genre = genre;
		this.barcode = barcode;
	}

	/**
	 * Constructs a new Item with the given title,and barcode. The genre is not
	 * needed, this is mainly for games which you do not need to specify a genre
	 * This constructor may only be invoked by subclasses.
	 * 
	 * @param title
	 *            the title of the item
	 * @param barcode
	 *            a unique integer identifier for the item
	 */
	protected AbstractItem(String title, int barcode)
	{
		this.title = title;
		this.barcode = barcode;
	}

	@Override
	public boolean isRented()
	{
		return rented;
	}

	@Override
	public SimpleDate getDueDate()
	{
		if (rented)
		{
			return dueDate;
		}
		return null;
	}

	@Override
	public String getTitle()
	{
		return title;
	}

	@Override
	public int getBarcode()
	{
		return barcode;
	}

	/**
	 * Returns a representation of the state of this object as a multiline
	 * string. The format is:
	 * 
	 * <pre>
	 *   type
	 *   title
	 *   (genre)
	 *   status
	 * </pre>
	 * 
	 * The status is either
	 * 
	 * <pre>
	 *   Rented: yyyy-mm-dd
	 * </pre>
	 * 
	 * or
	 * 
	 * <pre>
	 * Available
	 * </pre>
	 * 
	 * where "yyyy-mm-dd" is the current due date.
	 * 
	 * @return a string representation of this object
	 */
	public String toString()
	{
		String s = getClass().getSimpleName() + "\n";
		s = s + getTitle() + "\n";
		s = s + " (" + getGenre() + ")\n";
		if (isRented())
		{
			s = s + "Rented: " + getDueDate().toString();
		} else
			s = s + "Available";
		return s;
	}

	/**
	 * Determines whether this item is the same as another one based on its
	 * barcode.
	 * 
	 * @param obj
	 *            the object to compare to this item
	 * @return true if the given object is an AbstractItem with the same barcode
	 *         as this one
	 */
	public boolean equals(Object obj)
	{
		if (!(obj instanceof AbstractItem))
		{
			return false;
		}
		AbstractItem other = (AbstractItem) obj;
		return getBarcode() == other.getBarcode();
	}
}
