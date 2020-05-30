package model;

import java.io.Serializable;

/**
 * A class representing  a recipe
 *
 * @author Edvinas Andrijauskas
 * @version 1.0 - May 2020
 */

public class Recipe implements Serializable
{
  private int id;
  private String recipeName;
  private String description;
  private ListOfIngredients ingredients;
  private String instructions;
  private int preparationTime;
  private String category;
  private int ownerId;

  /**
   * Seven-argument constructor
   *
   * @param id              id of the recipe
   * @param recipeName      name of the recipe
   * @param description     description of the recipe
   * @param instructions    instructions of the recipe
   * @param preparationTime preparation time of recipe
   * @param category        category of the recipe
   * @param ownerId         owner id of the recipe
   */
  public Recipe(int id, String recipeName, String description,
      String instructions, int preparationTime, String category, int ownerId)
  {
    this.id = id;
    this.recipeName = recipeName;
    this.description = description;
    this.instructions = instructions;
    this.preparationTime = preparationTime;
    this.category = category;
    this.ownerId = ownerId;
  }

  /**
   * @param recipeName      name of the recipe
   * @param description     description of the recipe
   * @param instructions    instructions of the recipe
   * @param preparationTime preparation time of recipe
   * @param category        category of the recipe
   */

  public Recipe(String recipeName, String description, String instructions,
      int preparationTime, String category)
  {
    this.recipeName = recipeName;
    this.description = description;
    this.instructions = instructions;
    this.preparationTime = preparationTime;
    this.category = category;
  }

  /**
   * @param recipeName      name of the recipe
   * @param description     description of the recipe
   * @param ingredients     the list of the ingredients witch are inside the recipe
   * @param instructions    instructions instructions of the recipe
   * @param preparationTime preparationTime preparation time of recipe
   * @param category        category of the recipe
   */
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

  /**
   * Getter for the recipe id
   *
   * @return what id does the recipe has as an integer
   */
  public int getId()
  {
    return id;
  }

  /**
   * Getter for the recipe name
   *
   * @return recipe name as a String
   */
  public String getRecipeName()
  {
    return recipeName;
  }

  /**
   * Getter for the recipe description
   *
   * @return recipe description as a String
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Getter for the recipe description
   *
   * @return recipe description as a String
   */
  public ListOfIngredients getIngredients()
  {
    return ingredients;
  }

  /**
   * Setter for the ingredients
   *
   * @param ingredients ingredients of the recipe
   */
  public void setIngredients(ListOfIngredients ingredients)
  {
    this.ingredients = ingredients;
  }

  /**
   * Getter for the recipe instructions
   *
   * @return recipe instructions as a String
   */
  public String getInstructions()
  {
    return instructions;
  }

  /**
   * Getter for the preparation time
   *
   * @return preparation time as an integer
   */
  public int getPreparationTime()
  {
    return preparationTime;
  }

  /**
   * Getter for the recipe category
   *
   * @return recipe category as a String
   */
  public String getCategory()
  {
    return category;
  }

  /**
   * Getter for the owner id
   *
   * @return owner id as an integer
   */
  public int getOwnerId()
  {
    return ownerId;
  }

  /**
   * A string representation of instance variables
   *
   * @return recipe id,preparation time as an integers, recipe name, description, category and instructions as String
   */
  @Override public String toString()
  {
    return "Recipe{" + "id=" + id + ", recipeName='" + recipeName + '\''
        + ", description='" + description + '\'' + ", instructions='"
        + instructions + '\'' + ", preparationTime=" + preparationTime
        + ", category='" + category + '\'' + '}';
  }
}