package hw4;

/**
 * Abstraction of a search predicate for items. Subtypes
 * can customize the nature of the search (e.g., exact title, 
 * title keywords, genre, etc.).
 */
public interface SearchCondition
{
  /**
   * Determine whether the given item matches this 
   * search condition's criteria for inclusion.
   * @param item the item to be checked
   * @return true if the item matches this condition's
   *   criteria, false otherwise
   */
  public boolean matches(Item item);
}
