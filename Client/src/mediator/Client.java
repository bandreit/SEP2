package mediator;

import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.RemoteListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class Client implements ClientModel, RemoteListener<Recipe, Ingredient>
{
  public static final String HOST = "localhost";
  private String host;
  private PropertyChangeAction<Recipe, Ingredient> property;
  private LocalModel model;
  private RemoteModel remoteModel;

  public Client(LocalModel model)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this(model, HOST);
  }

  public Client(LocalModel model, String host)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.model = model;
    this.host = host;
    this.remoteModel = (RemoteModel) Naming
        .lookup("rmi://" + host + ":1099/Recipes");
    UnicastRemoteObject.exportObject(this, 0);
    this.remoteModel.addListener(this, "ADD","Comment");
    this.property = new PropertyChangeProxy<>(this, true);
  }

  @Override public int login(String username, String password)
      throws RemoteException, SQLException, Exception
  {
    try
    {
      return remoteModel.login(username, password);
    }
    catch (Exception e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void register(String user, String password, String email,
      String confirmPassword) throws RemoteException
  {
    try
    {
      remoteModel.register(user, password, email, confirmPassword);
    }
    catch (Exception e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws RemoteException
  {
    return remoteModel
        .createRecipe(recipeName, description, ingredients, instructions,
            preparationTime, category, userId);
  }

  @Override public RecipeList getRecipes() throws RemoteException, SQLException
  {
    return remoteModel.getRecipes();
  }

  @Override public RecipeList searchRecipes(String searchString)
      throws RemoteException, SQLException
  {
    return remoteModel.searchRecipes(searchString);
  }

  @Override public void deleteRecipe(int id)
      throws SQLException, RemoteException
  {
    remoteModel.deleteRecipe(id);
  }

  @Override public void close() throws Exception
  {
    //idk
  }

  @Override public RecipeList getRecipesForUser(int id)
      throws SQLException, RemoteException
  {
    return remoteModel.getRecipesForUser(id);
  }

  @Override public ListOfIngredients getIngredientsForRecipe(int recipeId)
      throws SQLException, RemoteException
  {
    return remoteModel.getIngredientsForRecipe(recipeId);
  }

  @Override public Recipe editRecipe(int id, String recipeName,
      String description, ListOfIngredients ingredients, String instructions,
      int preparationTime, String category, int userID) throws RemoteException
  {
    return remoteModel
        .editRecipe(id, recipeName, description, ingredients, instructions,
            preparationTime, category, userID);
  }

  @Override public String getComment(int id)
      throws SQLException, RemoteException
  {
    return remoteModel.getComment(id);
  }

  @Override public String createComment(int Id, int user, String text)
      throws SQLException, RemoteException
  {
    return remoteModel.createComment(Id, user, text);
  }

  @Override public void propertyChange(ObserverEvent<Recipe, Ingredient> event)
      throws RemoteException
  {
    property.firePropertyChange(event.getPropertyName(), event.getValue1(),
        event.getValue2());
  }

  @Override public boolean addListener(
      GeneralListener<Recipe, Ingredient> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Recipe, Ingredient> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }

  private String getExceptionMessage(Exception e)
  {
    String message = e.getMessage();
    if (message != null)
    {
      String[] messageArray = message.split(":");
      if (messageArray.length > 0)
      {
        message = messageArray[messageArray.length - 1];
      }
    }
    return message;
  }
}
