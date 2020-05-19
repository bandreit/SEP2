package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface LocalModel extends LocalSubject<Ingredient, Ingredient>
{
  boolean login(String user, String password) throws Exception;
  void register(String user, String password, String email,
      String confirmPassword) throws RemoteException, SQLException;
  void close(Recipe recipe);
  boolean isLoggedIn();

  void createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category) throws RemoteException;
  void addFullIngredientWithQtyAndAMeasurement(Ingredient ingredient);
  ListOfIngredients getListOfIngredients();

}
