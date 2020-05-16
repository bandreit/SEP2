package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable
{
  private String recipeName;
  private ListOfIngredients ingredients;
  private String description;

  public Recipe(String recipeName, ListOfIngredients ingredients,
      String description)
  {
    this.recipeName = recipeName;
    this.ingredients = ingredients;
    this.description = description;
  }

  public String getRecipeName()
  {
    return recipeName;
  }

  public void setRecipeName(String recipeName)
  {
    this.recipeName = recipeName;
  }

  public ListOfIngredients getIngredients()
  {
    return ingredients;
  }

  public void setIngredients(ListOfIngredients ingredients)
  {
    this.ingredients = ingredients;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  @Override public String toString()
  {
    return "Recipe{" + "recipeName='" + recipeName + '\'' + ", ingredients="
        + ingredients + ", description='" + description + '\'' + '}';
  }
}
