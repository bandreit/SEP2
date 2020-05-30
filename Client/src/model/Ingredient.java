package model;

import java.io.Serializable;

/**
 * A class representing the ingredient
 *
 * @author Grigore Budac
 * @version 1.0-May 2020
 */
public class Ingredient implements Serializable
{
  private int id;
  private String ingredient;
  private int amount;
  private String measurement;

  /**
   *
   * @param id - unique id for the ingredient
   * @param ingredient - ingredient name
   * @param amount - ingredient amount
   * @param measurement - ingredient measurement unit
   */
  public Ingredient(int id, String ingredient, int amount, String measurement)
  {
    this.id = id;
    this.ingredient = ingredient;
    this.amount = amount;
    this.measurement = measurement;
  }

  /**
   * getter for the ingredient amount
   * @return the ingredient amount as an int
   */
  public int getAmount()
  {
    return amount;
  }

  /**
   * setter for the ingredient amount
   * @param amount - the ingredient amount
   */
  public void setAmount(int amount)
  {
    this.amount = amount;
  }

  /**
   * getter for the ingredient name
   * @return the ingredient name as a string
   */
  public String getIngredient()
  {
    return ingredient;
  }

  /**
   * setter for the ingredient name
   * @param name the ingredient name as a string
   */
  public void setIngredient(String name)
  {
    this.ingredient = name;
  }

  /**
   * setter for the measurement
   * @param measurement as a string
   */
  public void setMeasurement(String measurement)
  {
    this.measurement = measurement;
  }

  /**
   * setter for the ingredient id
   * @param id as an int
   */
  public void setId(int id)
  {
    this.id = id;
  }

  /**
   * getter for the ingredient id
   * @return id as an int
   */
  public int getId()
  {
    return id;
  }

  /**
   * getter for the measurement
   * @return measurement as a string
   */
  public String getMeasurement()
  {
    return measurement;
  }
}
