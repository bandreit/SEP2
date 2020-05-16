package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface LocalModel extends LocalSubject<String, String>
{
  boolean login(String user, String password) throws Exception;
  void register(String user, String password,String email,String confirmPassword)
      throws RemoteException, SQLException;
  void close(Recipe recipe);
  boolean isLoggedIn();

  void createRecipe(String recipeName, ListOfIngredients ingredients, String description);
  void addAmount(String s);
  void addIngredient(String s);
  ListOfIngredients getListOfIngredients();

}
