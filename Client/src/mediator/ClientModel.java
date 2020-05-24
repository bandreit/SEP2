package mediator;

import model.ListOfIngredients;
import model.Recipe;
import model.RecipeList;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ClientModel extends LocalSubject<Recipe, Recipe>
{
  int login(String username,String password) throws RemoteException,
      SQLException, Exception;
  void register(String user, String password,String email,String confirmPassword)
      throws RemoteException, SQLException;
  Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws RemoteException;
  RecipeList getRecipes() throws RemoteException, SQLException;
  void deleteRecipe(int id) throws RemoteException,SQLException;

  void close() throws Exception;
}
