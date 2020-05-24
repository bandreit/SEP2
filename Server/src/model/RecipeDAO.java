package model;

import java.sql.SQLException;
import java.util.List;

public interface RecipeDAO
{
  Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws SQLException;
  void addIngredientsToRecipe(int recipeId, int ingredientID) throws SQLException;
  void deleteRecipe(String recipe, String category) throws SQLException;
  RecipeList getRecipes() throws SQLException;
}
