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
    implements LocalModel, LocalListener<Recipe, Ingredient>
{
  private ClientModel clientModel;
  private PropertyChangeAction<Recipe, Ingredient> property;
  private ListOfIngredients ingredientList;
  private RecipeList recipeList;

  public LocalModelManager() throws IOException
  {
    try
    {
      property = new PropertyChangeProxy<>(this, true);
      clientModel = new Client(this);
      clientModel.addListener(this);
      ingredientList = new ListOfIngredients();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

  }

  @Override public int login(String user, String password) throws Exception
  {
    return clientModel.login(user, password);
  }

  @Override public Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category) throws RemoteException
  {
    return clientModel
        .createRecipe(recipeName, description, ingredients, instructions,
            preparationTime, category, User.getInstance().getUserID());
  }

  @Override public Recipe editRecipe(int id, String recipeName,
      String description, ListOfIngredients ingredients, String instructions,
      int preparationTime, String category) throws RemoteException
  {
    return clientModel
        .editRecipe(id, recipeName, description, ingredients, instructions,
            preparationTime, category, User.getInstance().getUserID());
  }

  @Override public RecipeList getRecipesForUser()
      throws RemoteException, SQLException
  {
    return clientModel.getRecipesForUser(User.getInstance().getUserID());
  }

  @Override public ListOfIngredients getIngredientsForRecipe(int recipeId)
      throws SQLException, RemoteException
  {
    return clientModel.getIngredientsForRecipe(recipeId);
  }

  @Override public String getComment(int id)
      throws SQLException, RemoteException
  {
    return clientModel.getComment(id);
  }

  @Override public String createComment(int Id, int user, String text)
      throws SQLException, RemoteException
  {
    return clientModel.createComment(Id, user, text);
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

  @Override public void setUser(int userId)
  {
    User.getInstance(userId);
  }

  @Override public RecipeList getRecipes() throws RemoteException, SQLException
  {

    this.recipeList = clientModel.getRecipes();
    return recipeList;
  }

  @Override public RecipeList searchRecipes(String searchString)
      throws RemoteException, SQLException
  {
    return clientModel.searchRecipes(searchString);
  }

  @Override public void deleteRecipe(int id)
      throws RemoteException, SQLException
  {
    clientModel.deleteRecipe(id);
  }

  @Override public void propertyChange(ObserverEvent<Recipe, Ingredient> event)
  {

    property.firePropertyChange(event.getPropertyName(), event.getValue1(), null);
  }

  @Override public void close(Recipe recipe)
  {
    //hz
  }

  @Override public RecipeList getSavedRecipeList()
  {
    return recipeList;
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
}
