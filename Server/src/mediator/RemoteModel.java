package mediator;

import model.*;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface RemoteModel extends RemoteSubject<Recipe, Ingredient>, Remote
{
  int login(String username, String password) throws RemoteException, SQLException;
  int register(String user, String password, String email,
      String confirmPassword) throws RemoteException, SQLException;
  Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws RemoteException, SQLException;
  Recipe editRecipe(int id, String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws RemoteException, SQLException;
  RecipeList getRecipes() throws RemoteException, SQLException;
  RecipeList searchRecipes(String searchString) throws RemoteException, SQLException;
  RecipeList searchRecipesByIngredients(String searchString) throws RemoteException, SQLException;
  void deleteRecipe(int id) throws RemoteException,SQLException;
  RecipeList getRecipesForUser(int id) throws SQLException, RemoteException;
  ListOfIngredients getIngredientsForRecipe(int recipeId) throws SQLException, RemoteException;
  String getComment(int id)  throws SQLException, RemoteException;
  public String createComment(int Id, int user, String text)
      throws SQLException, RemoteException;
  ListOfDiscountItems getDiscountItems() throws SQLException, RemoteException;
}
