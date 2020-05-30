package model;

import java.sql.SQLException;
import java.util.List;

public interface RecipeDAO
{
  Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws SQLException;
  Recipe editRecipe(int id, String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws SQLException;
  void addIngredientsToRecipe(int recipeId, int ingredientID)
      throws SQLException;
  void deleteRecipe(int id) throws SQLException;
  RecipeList getRecipes() throws SQLException;
  RecipeList getRecipesForUser(int id) throws SQLException;
  RecipeList searchRecipes(String searchString) throws SQLException;
  RecipeList searchRecipesByIngredients(String searchString)
      throws SQLException;
}
