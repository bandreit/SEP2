package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable
{
  private int id;
  private String recipeName;
  private String description;
//  private ListOfIngredients ingredients;
  private String instructions;
  private int preparationTime;
  private String category;

  public Recipe(int id, String recipeName, String description, String instructions, int preparationTime,
      String category)
  {
    this.id = id;
    this.recipeName = recipeName;
    this.description = description;
    this.instructions = instructions;
    this.preparationTime = preparationTime;
    this.category = category;
  }

  public int getId()
  {
    return id;
  }

  @Override public String toString()
  {
    return "Recipe{" + "id=" + id + ", recipeName='" + recipeName + '\''
        + ", description='" + description + '\'' + ", instructions='"
        + instructions + '\'' + ", preparationTime=" + preparationTime
        + ", category='" + category + '\'' + '}';
  }

  public String getDescription()
  {
    return description;
  }

  public int getPreparationTime()
  {
    return preparationTime;
  }

  public String getCategory()
  {
    return category;
  }

  public String getInstructions()
  {
    return instructions;
  }

  public String getRecipeName()
  {
    return recipeName;
  }
}
