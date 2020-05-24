package mediator;

import model.ListOfIngredients;
import model.Recipe;
import model.RecipeList;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface RemoteModel extends RemoteSubject<Recipe, Recipe>, Remote
{
  int login(String username, String password)
      throws RemoteException, SQLException;
  void register(String user, String password, String email,
      String confirmPassword) throws RemoteException, SQLException;
  Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws RemoteException, SQLException;
  void deleteRecipe(String recipe, String category) throws RemoteException,SQLException;
  RecipeList getRecipes() throws RemoteException, SQLException;

}
