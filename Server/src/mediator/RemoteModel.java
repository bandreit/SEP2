package mediator;

import model.Ingredient;
import model.ListOfIngredients;
import model.Recipe;
import model.RecipeList;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface RemoteModel extends RemoteSubject<Recipe, Ingredient>, Remote
{
  int login(String username, String password) throws RemoteException, SQLException;
  void register(String user, String password, String email,
      String confirmPassword) throws RemoteException, SQLException;
  Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws RemoteException, SQLException;
  Recipe editRecipe(int id, String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws RemoteException, SQLException;
  RecipeList getRecipes() throws RemoteException, SQLException;
  RecipeList searchRecipes(String searchString) throws RemoteException, SQLException;
  void deleteRecipe(int id) throws RemoteException,SQLException;
  RecipeList getRecipesForUser(int id) throws SQLException, RemoteException;
  ListOfIngredients getIngredientsForRecipe(int recipeId) throws SQLException, RemoteException;
  String getComment(int id)  throws SQLException, RemoteException;
  public String createComment(int Id, String userName, String text)
      throws SQLException, RemoteException;
}
