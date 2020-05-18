package mediator;

import model.ListOfIngredients;
import model.Recipe;
import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ClientModel extends LocalSubject<Recipe, Recipe>
{
  boolean login(String username,String password) throws RemoteException,
      SQLException, Exception;
  void register(String user, String password,String email,String confirmPassword)
      throws RemoteException, SQLException;
  void createRecipe(String recipeName, ListOfIngredients ingredients, String description);

  void close() throws Exception;
}
