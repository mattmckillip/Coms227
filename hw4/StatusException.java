package hw4;

/**
 * Exception type thrown for invalid operations such
 * as attempting to rent an item that is already 
 * rented.
 */
public class StatusException extends Exception
{
  /**
   * Constructs a StatusException with an empty message.
   */
  public StatusException()
  {
    super();
  }
  
  /**
   * Constructs a StatusException with the given message.
   * @param message
   *   message for this exception
   */
  public StatusException(String message)
  {
    super(message);
  }
}
