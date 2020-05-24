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
  void deleteRecipe(String recipe, String category) throws SQLException;
  public void close();
  RecipeList getRecipes() throws SQLException;
}
