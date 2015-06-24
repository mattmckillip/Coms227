package hw4;

import java.util.ArrayList;

/**
 * A VideoStore consists of a list of items and a list of customers
 * who can rent items.
 */
public class VideoStore
{
  /**
   * The items in this store.
   */
  private ArrayList<Item> items;
  
  /**
   * The list of customers of this store.
   */
  private ArrayList<Customer> customers;
  
  /**
   * Constructs a VideoStore that initially has no items and no customers.
   */
  public VideoStore()
  {
    items = new ArrayList<Item>();
    customers = new ArrayList<Customer>();
  }
  
  /**
   * Adds an item to this store's list of items, provided
   * that there is not already an item with the same barcode.
   * @param item 
   *   the item to be added
   */
  public void addItem(Item item)
  {
    if (!items.contains(item))
    {
      items.add(item);
    }
  }
  
  /**
   * Adds a customer to this store's list of customers.
   * @param customer the customer to be added
   */
  public void addCustomer(Customer customer)
  {
    customers.add(customer);
  }
  
  /**
   * Returns the customer with the given name
   * @param name 
   *   the name of the customer to search for
   * @return 
   *   the customer 
   */
  public Customer findUser(String name)
  {
    for (Customer p : customers)
    {
      if (name.equals(p.getName()))
      {
        return p;
      }
    }
    return null;
  }
  
  /**
   * Search the store's collection of for items satisfying the
   * given SearchCondition.
   * @param condition 
   *   the SearchCondition
   * @return 
   *   list of items satisfying the condition
   */
  public ArrayList<Item> search(SearchCondition condition)
  {
    ArrayList<Item> results = new ArrayList<Item>();
    for (Item item : items)
    {
      if (condition.matches(item))
      {
        results.add(item);
      }
    }
    return results;   
  }
  
}
