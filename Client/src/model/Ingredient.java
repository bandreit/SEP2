package model;

public class Ingredient
{
  private String ingredient;
  private String amount;
  public Ingredient(String ingredient, String amount)
  {
    this.ingredient=ingredient;
    this.amount=amount;
  }

  public String getAmount()
  {
    return amount;
  }

  public void setAmount(String amount)
  {
    this.amount = amount;
  }

  public String getIngredient()
  {
    return ingredient;
  }

  public void setIngredient(String name)
  {
    this.ingredient = name;
  }
}
