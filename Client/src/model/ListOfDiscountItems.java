package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list of discounted items
 *
 *@author Andrei Bostan
 *@version 1.0-May 2020
 */
public class ListOfDiscountItems implements Serializable
{
  private ArrayList<DiscountItem> discountItems;

  /**
   * Zero argument constructor.
   * the discountItems Arraylist is initialized
   */
  public ListOfDiscountItems()
  {
    discountItems = new ArrayList<>();
  }

  /**
   *  Adding a discount item to the ArrayList of the discount items
   *
   * @param discountItem a DiscountItem object with information
   */
  public void addDiscountItem(DiscountItem discountItem)
  {
    discountItems.add(discountItem);
  }

  /**
   * A method returning a DiscountItem object from the ArrayList by the index
   * @param index an integer representing the index from the ArrayLst
   * @return DiscountItem object found in the position of index
   */
  public DiscountItem getDiscountItem(int index)
  {
    return discountItems.get(index);
  }

  /**
   * A method returning the representation of instance variables
   * @return String containing all the discount items displayed
   */
  @Override public String toString()
  {
    return "ListOfDiscountItems{" + "discountItems=" + discountItems + '}';
  }

  /**
   * A getter for the list of discounts size
   *
   * @return list of discount items size as an integer
   */
  public int getSize()
  {
    return discountItems.size();
  }

  /**
   * A method returning a String object with the URL link of a Discount Item found by the name
   * @param name String object representing the name of a discount item
   * @return  a String object returning either a URL or a null object if no such object is found
   */
  public String getDiscountItemLinkByName(String name)
  {
    for (int i = 0; i < discountItems.size(); i++)
    {
      if (discountItems.get(i).getTitle().equals(name))
      {
        return discountItems.get(i).getLink();
      }
    }
    return null;
  }
}
