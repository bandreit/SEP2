package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListOfDiscountItemsTest
{
  private ListOfDiscountItems list;
  private ListOfDiscountItems populatedList;
  private DiscountItem discountItem;
  private DiscountItem discountItem2;
  private DiscountItem discountItem3;

  @BeforeEach void setUp()
  {
    list = new ListOfDiscountItems();
    populatedList = new ListOfDiscountItems();
    discountItem = new DiscountItem(0, "Beef", "www.google.com/beef.png",
        "Lunch", "20", "30", "www.google.com/beef");
    discountItem2 = new DiscountItem(1, "Chicken", "www.google.com/chicken.png",
        "Lunch", "10", "25", "www.google.com/chicken");
    discountItem3 = new DiscountItem(3, "Eggs", "www.google.com/eggs.png",
        "Breakfast", "30", "40", "www.google.com/eggs");
    populatedList.addDiscountItem(discountItem);
    populatedList.addDiscountItem(discountItem2);
    populatedList.addDiscountItem(discountItem3);
  }

  @Test void addDiscountItemZero()
  {
    // no need to be tested
  }

  @Test void addDiscountItemOne()
  {
    list.addDiscountItem(discountItem);
    assertEquals(list.getDiscountItem(0), discountItem);
  }

  @Test void addDiscountItemMany()
  {
    list.addDiscountItem(discountItem);
    list.addDiscountItem(discountItem2);
    list.addDiscountItem(discountItem3);
    assertEquals(list.getDiscountItem(0), discountItem);
    assertEquals(list.getDiscountItem(1), discountItem2);
    assertEquals(list.getDiscountItem(2), discountItem3);
  }

  @Test void addDiscountItemBoundary()
  {
    // no need to test
  }

  @Test void addDiscountItemExceptions()
  {
    // no need to test
  }

  @Test void getDiscountItemZero()
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getDiscountItem(0);
    });
  }

  @Test void getDiscountItemOne()
  {
    list.addDiscountItem(discountItem);
    assertEquals(list.getDiscountItem(0), discountItem);
  }

  @Test void getDiscountItemMany()
  {
    list.addDiscountItem(discountItem);
    list.addDiscountItem(discountItem2);
    list.addDiscountItem(discountItem3);
    assertEquals(list.getDiscountItem(0), discountItem);
    assertEquals(list.getDiscountItem(1), discountItem2);
    assertEquals(list.getDiscountItem(2), discountItem3);
  }

  @Test void getDiscountItemBoundary()
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getDiscountItem(-1);
    });

    list.addDiscountItem(discountItem);
    list.addDiscountItem(discountItem2);
    list.addDiscountItem(discountItem3);
    assertEquals(list.getDiscountItem(0), discountItem);
    assertEquals(list.getDiscountItem(1), discountItem2);
    assertEquals(list.getDiscountItem(2), discountItem3);

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getDiscountItem(Integer.MAX_VALUE);
    });
  }

  @Test void getDiscountItemExceptions()
  {
    list.addDiscountItem(discountItem);
    list.addDiscountItem(discountItem2);
    list.addDiscountItem(discountItem3);

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getDiscountItem(-100);
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getDiscountItem(175);
    });
  }

  @Test void getSizeZero()
  {
    assertEquals(0, list.getSize());
  }

  @Test void getSizeOne()
  {
    list.addDiscountItem(discountItem);
    assertEquals(1, list.getSize());
  }

  @Test void getSizeMany()
  {
    assertEquals(3, populatedList.getSize());
  }

  @Test void getDiscountItemLinkByNameZero()
  {
    assertNull(populatedList.getDiscountItemLinkByName("agae"));
  }

  @Test void getDiscountItemLinkByNameOne()
  {
    assertEquals("www.google.com/beef", populatedList.getDiscountItemLinkByName("Beef"));
  }

  @Test void getDiscountItemLinkByNameMany()
  {
    assertEquals("www.google.com/beef", populatedList.getDiscountItemLinkByName("Beef"));
    assertEquals("www.google.com/chicken", populatedList.getDiscountItemLinkByName("Chicken"));
    assertEquals("www.google.com/eggs", populatedList.getDiscountItemLinkByName("Eggs"));
  }

  @Test void getDiscountItemLinkByNameBoundary()
  {
    assertNull(populatedList.getDiscountItemLinkByName(null));
    assertEquals("www.google.com/beef", populatedList.getDiscountItemLinkByName("Beef"));
    assertEquals("www.google.com/chicken", populatedList.getDiscountItemLinkByName("Chicken"));
    assertEquals("www.google.com/eggs", populatedList.getDiscountItemLinkByName("Eggs"));
    assertNull(populatedList.getDiscountItemLinkByName("1000001"));
  }

  @Test void getDiscountItemLinkByNameExceptions()
  {
    //not possible
  }
}