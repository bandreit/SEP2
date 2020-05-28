package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface LocalModel extends LocalSubject<Recipe, Ingredient>
{
  int login(String user, String password) throws Exception;
  void register(String user, String password, String email,
      String confirmPassword) throws RemoteException, SQLException;
  void close(Recipe recipe);

  Recipe createRecipe(String recipeName, String description,
    ListOfIngredients ingredients, String instructions, int preparationTime,
    String category) throws RemoteException;
  Recipe editRecipe(int id, String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category) throws RemoteException;
  void addFullIngredientWithQtyAndAMeasurement(Ingredient ingredient);
  ListOfIngredients getListOfIngredients();
  void removeIngredient(String ingredientName);
  void setUser(int userId);
  RecipeList getRecipes() throws RemoteException, SQLException;
  RecipeList searchRecipes(String searchString) throws RemoteException, SQLException;
  void deleteRecipe(int id) throws RemoteException, SQLException;
  RecipeList getRecipesForUser() throws RemoteException, SQLException;
  ListOfIngredients getIngredientsForRecipe(int recipeId)
      throws SQLException, RemoteException;
  String getComment(int id)  throws SQLException, RemoteException;
  public String createComment(int Id, String userName, String text)
      throws SQLException, RemoteException;
}
