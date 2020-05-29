package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfDiscountItems implements Serializable
{
  private ArrayList<DiscountItem> discountItems;

  public ListOfDiscountItems()
  {
    discountItems = new ArrayList<>();
  }

  public void addDiscountItem(DiscountItem discountItem)
  {
    discountItems.add(discountItem);
  }

  public void removeDiscountItem(DiscountItem discountItem)
  {
    discountItems.remove(discountItem);
  }

  public DiscountItem getDiscountItem(int index)
  {
    return discountItems.get(index);
  }

  @Override public String toString()
  {
    return "ListOfDiscountItems{" + "discountItems=" + discountItems + '}';
  }

  public int getSize()
  {
    return discountItems.size();
  }

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
