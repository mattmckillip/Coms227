
package hw4;

/**
 * Implementation of SearchCondition that matches items
 * based on the genre (not case sensitive).
 */
public class GenreSearch implements SearchCondition
{
  /**
   * The genre we are searching for.
   */
  private final String genre;
  
  /**
   * Constructs a GenreSearch for the given value.
   * @param genre 
   *   the genre to search for
   */
  public GenreSearch(String genre)
  {
    this.genre = genre;
  }
  
  @Override
  public boolean matches(Item item)
  {
    return genre.equalsIgnoreCase(item.getGenre());
  }

}
