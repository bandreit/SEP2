package model;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeList implements Serializable
{
  private ArrayList<Recipe> recipes;

  public RecipeList()
  {
    this.recipes = new ArrayList<>();
  }

  public int getSize()
  {
    return recipes.size();
  }

  public Recipe getRecipe(int index)
  {
    return recipes.get(index);
  }

  public Recipe getRecipeById(int id) {
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

  public void addRecipe(Recipe recipe)
  {
    if (recipe == null)
    {
      throw new IllegalArgumentException("A null recipe");
    }
    recipes.add(recipe);
  }

}
