package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable
{
  private String recipeName;
  private String description;
  private ListOfIngredients ingredients;
  private String instructions;
  private int preparationTime;
  private String category;

  public Recipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category)
  {
    this.recipeName = recipeName;
    this.description = description;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.preparationTime = preparationTime;
    this.category = category;
  }

  @Override public String toString()
  {
    return "Recipe{" + "recipeName='" + recipeName + '\'' + ", description='"
        + description + '\'' + ", ingredients=" + ingredients
        + ", instructions='" + instructions + '\'' + ", preparationTime="
        + preparationTime + ", category='" + category + '\'' + '}';
  }
}