package model;

public class Ingredient
{
  private String ingredient;
  private String amount;
  private String measurement;
  public Ingredient(String ingredient, String amount,String measurement)
  {
    this.ingredient=ingredient;
    this.amount=amount;
    this.measurement=measurement;
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

  public void setMeasurement(String measurement)
  {
    this.measurement = measurement;
  }

  public String getMeasurement()
  {
    return measurement;
  }
}
