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

  @Override public void register(String user, String password, String email,
      String confirmPassword) throws RemoteException, SQLException
  {
    clientModel.register(user, password, email, confirmPassword);
  }

  @Override public void createRecipe(String recipeName,
      ListOfIngredients ingredients, String description)
  {

  }

  @Override public void addAmount(String s)
  {
    this.amount = s;
  }

  @Override public void addIngredient(String s)
  {
    this.ingredient = s;
  }

  @Override public void addMeasurement(String s)
  {
    this.measurement = s;
  }

  @Override public void addFullIngredientWithQtyAndAMeasurement(
      Ingredient ingredient)
  {
    ingredientList.addIngredient(ingredient);
    System.out.println(ingredient.getIngredient());
    System.out.println(ingredient.getAmount());
    System.out.println(ingredient.getMeasurement());
    property.firePropertyChange("addIngredient", null,
        ingredient);// ??????? WHAT TO DO IN HERE?
  }

  @Override public ListOfIngredients getListOfIngredients()
  {
    ListOfIngredients ingredients = new ListOfIngredients();
    Ingredient ing = new Ingredient(ingredient, amount, measurement);
    ingredients.addIngredient(ing);
    return ingredients;
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
