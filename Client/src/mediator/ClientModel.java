package mediator;

import model.*;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ClientModel extends LocalSubject<Recipe, Ingredient>
{
  int login(String username, String password)
      throws RemoteException, SQLException, Exception;
  void register(String user, String password, String email,
      String confirmPassword) throws RemoteException, SQLException;
  Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws RemoteException;
  RecipeList getRecipes() throws RemoteException, SQLException;
  RecipeList searchRecipes(String searchString)
      throws RemoteException, SQLException;
  RecipeList searchRecipesByIngredients(String searchString)
      throws RemoteException, SQLException;
  void deleteRecipe(int id) throws RemoteException, SQLException;

  void close() throws Exception;
  RecipeList getRecipesForUser(int id) throws SQLException, RemoteException;
  ListOfIngredients getIngredientsForRecipe(int recipeId)
      throws SQLException, RemoteException;
  Recipe editRecipe(int id, String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userID) throws RemoteException;
  String getComment(int id) throws SQLException, RemoteException;

  public String createComment(int Id, int user, String text)
      throws SQLException, RemoteException;

  ListOfDiscountItems getDiscountItems() throws SQLException, RemoteException;
}
