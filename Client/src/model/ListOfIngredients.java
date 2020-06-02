package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing the list of ingredients
 *
 * @author Grigore Budac
 * @version 1.0-May 2020
 */
public class ListOfIngredients implements Serializable
{
  private ArrayList<Ingredient> ingredients;

  /**
   * The constructor initiates the class instances
   */
  public ListOfIngredients()
  {
    ingredients = new ArrayList<>();
  }

  /**
   * add an ingredient to the ingredient list
   * @param ingredient as an Ingredient object
   */
  public void addIngredient(Ingredient ingredient)
  {
    if (ingredient == null)
    {
      throw new IllegalArgumentException("Empty ingredient");
    }
    ingredients.add(ingredient);
  }

  /**
   * remove an ingredient from the ingredient list
   * @param ingredientName as a string
   */
  public void removeIngredient(String ingredientName)
  {
    for (int i = 0; i < ingredients.size(); i++)
    {
      if (ingredients.get(i).getIngredient().equals(ingredientName))
      {
        ingredients.remove(ingredients.get(i));
      }
    }
  }

  /**
   * get an ingredient from the list
   * @param index - ingredient list
   * @return the ingredient object
   */
  public Ingredient getIngredient(int index)
  {
    return ingredients.get(index);
  }

  /**
   * A string representation of instance variables
   * @return the stringified content
   */
  @Override public String toString()
  {
    return "ListOfIngredients{" + "ingredients=" + ingredients + '}';
  }

  /**
   * get the ingredient list size
   * @return size as an int
   */
  public int getSize()
  {
    return ingredients.size();
  }
}
