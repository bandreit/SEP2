package model;

import java.sql.SQLException;

public interface RecipeDAO
{
  Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category) throws SQLException;
  void addIngredientsToRecipe(int recipeId, int ingredientID) throws SQLException;
}