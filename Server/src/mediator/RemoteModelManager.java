package mediator;

import model.ListOfIngredients;
import model.Model;
import model.Recipe;
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

public class RemoteModelManager implements RemoteModel, LocalListener<Recipe, Recipe>
{
  private PropertyChangeAction<Recipe, Recipe> property;
  private Model model;

  public RemoteModelManager(Model model)
      throws RemoteException, MalformedURLException
  {
    this.property = new PropertyChangeProxy<>(this, true);
    this.model = model;
    this.model.addListener(this, "add", "connected");
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


  @Override public boolean login(String username, String password)
      throws SQLException
  {
    return model.login(username,password);
  }

  @Override public void register(String user, String password, String email,
      String confirmPassword) throws SQLException, RemoteException
  {
    model.register(user, password, email, confirmPassword);
  }

  @Override public void createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category)
      throws RemoteException, SQLException
  {
    model.createRecipe(recipeName, description, ingredients, instructions, preparationTime, category);
  }

  @Override public boolean addListener(
      GeneralListener<Recipe, Recipe> listener, String... propertyNames)
      throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Recipe, Recipe> listener, String... propertyNames)
      throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public void propertyChange(ObserverEvent<Recipe, Recipe> event)
  {
    property.firePropertyChange(event.getPropertyName(), null, event.getValue2());
  }

  @Override public void close()
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
