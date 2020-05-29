package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface Model extends LocalSubject<Recipe, Recipe>
{
  int login(String username,String password) throws SQLException;
  void register(String user, String password,String email,String confirmPassword)
      throws SQLException, RemoteException;
  Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws SQLException;
  Recipe editRecipe(int id, String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws SQLException;
  void deleteRecipe(int id) throws SQLException;
  public void close();
  RecipeList getRecipes() throws SQLException;
  RecipeList getRecipesForUser(int id) throws SQLException;
  RecipeList searchRecipes(String searchString) throws SQLException;
  ListOfIngredients getIngredientsForRecipe(int recipeId) throws SQLException;
  String getComment(int id)  throws SQLException, RemoteException;
  public String createComment(int Id, int user, String text)
      throws SQLException, RemoteException;
  RecipeList searchRecipesByIngredients(String searchString)
      throws SQLException;
}
