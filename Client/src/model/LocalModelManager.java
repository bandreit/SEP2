package model;

import mediator.ClientModel;
import mediator.Client;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class LocalModelManager
    implements LocalModel, LocalListener<Recipe, Recipe>
{
  private ClientModel clientModel;
  private PropertyChangeAction<Ingredient, Ingredient> property;
  private ListOfIngredients ingredientList;
  private String amount;
  private String ingredient;
  private String measurement;
  private boolean loggedIn;

  public LocalModelManager() throws IOException
  {
    try
    {
      this.loggedIn = false;
      clientModel = new Client(this);
      clientModel.addListener(this);
      ingredientList = new ListOfIngredients();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    property = new PropertyChangeProxy<>(this, true);
  }

  @Override public boolean login(String user, String password) throws Exception
  {
    return clientModel.login(user, password);
  }

  @Override public boolean isLoggedIn()
  {
    return loggedIn;
  }

  @Override public void createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category) throws RemoteException
  {
    clientModel.createRecipe(recipeName, description, ingredients, instructions, preparationTime, category);
  }

  @Override public void register(String user, String password, String email,
      String confirmPassword) throws RemoteException, SQLException
  {
    clientModel.register(user, password, email, confirmPassword);
  }


  @Override public void addFullIngredientWithQtyAndAMeasurement(
      Ingredient ingredient)
  {
    ingredientList.addIngredient(ingredient);
    property.firePropertyChange("addIngredient", null, ingredient);
  }

  @Override public ListOfIngredients getListOfIngredients()
  {
    return ingredientList;
  }

  @Override public void removeIngredient(String ingredientName)
  {
    ingredientList.removeIngredient(ingredientName);
  }

  @Override public void propertyChange(ObserverEvent<Recipe, Recipe> event)
  {
//    String message = "Message: Added " + event.getValue2();
//        property.firePropertyChange("broadcast", null, ingredient);
  }

  @Override public boolean addListener(
      GeneralListener<Ingredient, Ingredient> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Ingredient, Ingredient> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }

  @Override public void close(Recipe recipe)
  {
    //hz
  }
}
