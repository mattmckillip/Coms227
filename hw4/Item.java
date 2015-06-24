package hw4;

/**
 * An item represents a movie or game that can be rented
 * from a video store. Each item has a title, a genre,
 * and a unique integer identifier called a barcode.
 * An item can be rented or available, and if rented
 * it has a due date.
 */
public interface Item
{
  /**
   * Rents this item if it is not already rented and sets the
   * due date.
   * @param today 
   *   the date on which this item is being rented
   * @throws StatusException
   *   if the item cannot be rented
   */
  void setRented(SimpleDate today) throws StatusException;

  /**
   * Returns this item, if it is currently rented.
   * @param today 
   *   the date on which the item is being returned
   * @throws StatusException
   *   if the item is not currently rented
   */
  void setReturned() throws StatusException;
  
  /**
   * Returns the cost to rent this item.
   * @return
   *   cost to rent the item
   */
  int getRentalCost();

  /**
   * Calculates the late fee (or bonus) that would be charged (or
   * applied) for returning the item on the given date.
   * @param today 
   *   the date on which the item is being returned
   * @return
   *   the late fee or bonus for returning the item on the given date,
   *   or zero if the item is not currently rented
   */
  int calculateLateFee(SimpleDate today);

  /**
   * Returns a String representing the genre of this item
   * @return 
   *   genre of this item
   */
  String getGenre();

  /**
   * Determines whether this item is currently rented.
   * @return 
   *   true if this item is rented, false otherwise
   */
  boolean isRented();

  /**
   * Returns the due date for this item if it is currently rented,
   * or null if the item is not rented.
   * @return 
   *   due date for this item
   */
  SimpleDate getDueDate();

  /**
   * Returns the title of this item.
   * @return 
   *   title of this item
   */
  String getTitle();

  /**
   * Returns the integer barcode for this item.
   * @return 
   *   barcode of this item
   */
  int getBarcode();

}
