package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class RemoteModelManager implements RemoteModel, LocalListener<Recipe, Ingredient>
{
  private PropertyChangeAction<Recipe, Ingredient> property;
  private Model model;

  public RemoteModelManager(Model model)
      throws RemoteException, MalformedURLException
  {
    this.property = new PropertyChangeProxy<>(this, true);
    this.model = model;
    startRegistry();
    startServer();
  }

  private static void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }
    catch (ExportException e)
    {
      System.out.println("Registry already started? " + e.getMessage());
    }
  }

  public void startServer() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("Recipes", this);
    System.out.println("Server started...");
  }


  @Override public int login(String username, String password)
      throws SQLException
  {
    return model.login(username,password);
  }

  @Override public void register(String user, String password, String email,
      String confirmPassword) throws SQLException, RemoteException
  {
    model.register(user, password, email, confirmPassword);
  }

  @Override public Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId)
      throws RemoteException, SQLException
  {
    Recipe recipe = model
        .createRecipe(recipeName, description, ingredients, instructions,
            preparationTime, category, userId);
    property.firePropertyChange("ADD", recipe, null);
    return recipe;
  }

  @Override public void deleteRecipe(int id) throws RemoteException,SQLException
  {
    model.deleteRecipe(id);
  }

  @Override public RecipeList getRecipes() throws SQLException
  {
    return model.getRecipes();
  }

  @Override public RecipeList searchRecipes(String searchString)
      throws RemoteException, SQLException
  {
    return model.searchRecipes(searchString);
  }

  @Override public RecipeList getRecipesForUser(int id) throws SQLException, RemoteException
  {
    return model.getRecipesForUser(id);
  }

  @Override public boolean addListener(
      GeneralListener<Recipe, Ingredient> listener, String... propertyNames)
      throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Recipe, Ingredient> listener, String... propertyNames)
      throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public void propertyChange(ObserverEvent<Recipe, Ingredient> event)
  {
    property.firePropertyChange(event.getPropertyName(), event.getValue1(), null);
  }

  public void close()
  {
    property.close();
    try
    {
      UnicastRemoteObject.unexportObject(this, true);
    }
    catch (Exception e)
    {
    }
  }
}
