package hw4;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Date consisting of a year, month, and day.
 */
public class SimpleDate
{
  private static final long MILLIS_IN_24_HOURS = 1000 * 60 * 60 * 24;
  private Date date;
  
  /**
   * Constructs a SimpleDate with the given year, month, and day.
   * @param year four-digit year
   * @param month 1-based month number
   * @param day 1-based day of month
   */
  public SimpleDate(int year, int month, int day)
  {
    // month number is 0-based for Calendar
    date = new GregorianCalendar(year, month - 1, day).getTime();
  }
  
  /**
   * Constructs a SimpleDate that is a given number of days
   * after an existing date.
   * @param existing the given SimpleDate
   * @param additionalDays the number of days to be added to the existing date
   */
  public SimpleDate(SimpleDate existing, int additionalDays)
  {
    Calendar cal = new GregorianCalendar();
    cal.setTime(existing.date);
    cal.add(Calendar.DAY_OF_MONTH, additionalDays);
    date = cal.getTime();
  }

  /**
   * Determines whether this date is strictly earlier than the
   * given date.
   * @param other
   * @return true if this date is strictly before the given date.
   *   false otherwise
   */
  public boolean isBefore(SimpleDate other)
  {
    return daysUntil(other) > 0;
  }
  
  /**
   * Returns the number of days from this date until the given date.
   * Returns a negative number if this date after the given date.
   * @param other the future date
   * @return number of days until the given date (negative if it is in the past)
   */
  public int daysUntil(SimpleDate other)
  {
    long millis;
    int days;
    if (!date.before(other.date))
    {
      millis = date.getTime() - other.date.getTime();
      days = -((int) (millis / MILLIS_IN_24_HOURS));
    }
    else
    {
      millis = other.date.getTime() - date.getTime();
      days = (int) (millis / MILLIS_IN_24_HOURS);     
    }
    return days;
  }
  
  @Override
  public String toString()
  {
    Calendar cal = new GregorianCalendar();
    cal.setTime(date);
    return String.format("%4d-%02d-%02d", 
        cal.get(Calendar.YEAR),
        cal.get(Calendar.MONTH) + 1,
        cal.get(Calendar.DAY_OF_MONTH));
  }
}
