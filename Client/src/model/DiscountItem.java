package model;

import java.io.Serializable;

/**
 * A class representing the discount item
 *
 * @author Grigore Budac
 * @version 1.0-May 2020
 */
public class DiscountItem implements Serializable
{
  private int id;
  private String title;
  private String imageUrl;
  private String category;
  private String discountPrice;
  private String normalPrice;
  private String link;

  /**
   * The constructor initiates the class instances
   * @param id - discount unique id
   * @param title - product's title
   * @param imageUrl - product's image
   * @param category - product's category
   * @param discountPrice - product's price with discount
   * @param normalPrice - product's price without discount
   * @param link - initial discount url
   */
  public DiscountItem(int id, String title, String imageUrl, String category,
      String discountPrice, String normalPrice, String link)
  {
    this.id = id;
    this.title = title;
    this.imageUrl = imageUrl;
    this.category = category;
    this.discountPrice = discountPrice;
    this.normalPrice = normalPrice;
    this.link = link;
  }

  /**
   * getter for discount id
   * @return discount id as an integer
   */
  public int getId()
  {
    return id;
  }

  /**
   * getter for the discount title
   * @return title as a string
   */
  public String getTitle()
  {
    return title;
  }

  /**
   * getter for the discount image
   * @return imageUrl as a string
   */
  public String getImageUrl()
  {
    return imageUrl;
  }

  /**
   * getter for the discount category name
   * @return category name as a string
   */
  public String getCategory()
  {
    return category;
  }

  /**
   * getter for the discount price
   * @return discount price as a string
   */
  public String getDiscountPrice()
  {
    return discountPrice;
  }

  /**
   * getter for the normal price
   * @return normal price as a string
   */
  public String getNormalPrice()
  {
    return normalPrice;
  }

  /**
   * getter for the discount url
   * @return discount url as a string
   */
  public String getLink()
  {
    return link;
  }
}
