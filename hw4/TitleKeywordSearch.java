package hw4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Matt
 * 
 */
public class TitleKeywordSearch implements SearchCondition
{
	private boolean allowSubstrings;
	private String keyword;

	public TitleKeywordSearch(String keyword, boolean allowSubstrings)
	{
		this.allowSubstrings = allowSubstrings;
		this.keyword = keyword.toLowerCase();
	}

	@Override
	public boolean matches(Item item)
	{
		String title = item.getTitle().toLowerCase();

		// allow substrings
		if (allowSubstrings)
		{
			return title.contains(keyword);
		}
		// must be exactly the keyword
		else
		{
			// split the string by spaces and store it in an array
			String[] split = title.split("\\s+");
			// iterate through the array to see if a word is exactly the keyword
			for (int i = 0; i < split.length; i++)
			{
				if (split[i].equals(keyword))
				{
					// found a match so return true
					return true;
				}
			}
			// didnt find any matches so return false
			return false;
		}
	}
}
