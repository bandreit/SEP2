package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list of recipes
 *
 * @author Edvinas Andrijauskas
 * @version 1.0 - May 2020
 */

public class RecipeList implements Serializable
{
  private ArrayList<Recipe> recipes;

  /**
   * One - argument constructor
   * recipes are initialised as ArrayList
   */
  public RecipeList()
  {
    this.recipes = new ArrayList<>();
  }

  /**
   * A getter for the recipes size
   *
   * @return recipe size as an integer
   */
  public int getSize()
  {
    return recipes.size();
  }

  /**
   * A getter for specific recipe by index from arrayList
   *
   * @param index index is an integer, which shows the position in the arrayList
   * @return asked recipe
   */
  public Recipe getRecipe(int index)
  {
    return recipes.get(index);
  }

  /**
   * A getter for specific recipe by id from the arrayList
   * @param id id of the recipe
   * @return asked recipe
   */
  public Recipe getRecipeById(int id)
  {
    for (int i = 0; i < recipes.size(); i++)
    {
      Recipe recipe = recipes.get(i);
      if (recipe.getId() == id)
      {
        return recipe;
      }
    }
    return null;
  }

}
