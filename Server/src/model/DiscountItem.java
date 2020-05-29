package model;

import java.io.Serializable;

public class DiscountItem implements Serializable
{
  private int id;
  private String title;
  private String imageUrl;
  private String category;
  private String discountPrice;
  private String normalPrice;
  private String link;

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

  public int getId()
  {
    return id;
  }

  public String getTitle()
  {
    return title;
  }

  public String getImageUrl()
  {
    return imageUrl;
  }

  public String getCategory()
  {
    return category;
  }

  public String getDiscountPrice()
  {
    return discountPrice;
  }

  public String getNormalPrice()
  {
    return normalPrice;
  }

  public String getLink()
  {
    return link;
  }
}
