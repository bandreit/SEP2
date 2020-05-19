package model;

import java.io.Serializable;

public class Ingredient implements Serializable
{
  private int id;
  private String ingredient;
  private int amount;
  private String measurement;

  public Ingredient(int id, String ingredient, int amount, String measurement)
  {
    this.id = id;
    this.ingredient = ingredient;
    this.amount = amount;
    this.measurement = measurement;
  }

  public int getAmount()
  {
    return amount;
  }

  public void setAmount(int amount)
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

  public void setId(int id)
  {
    this.id = id;
  }

  public int getId()
  {
    return id;
  }

  public String getMeasurement()
  {
    return measurement;
  }
}
