package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelManager implements Model
{
//  private RecipeList recipeList;
  private UserList userList;
  private PropertyChangeAction<Recipe, Recipe> property;

  public ModelManager()
  {
//    this.recipeList = new RecipeList();
    this.userList = new UserList();
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override public void close()
  {
    property.close();
  }

  @Override public RecipeList getRecipes() throws SQLException
  {
    return RecipeDAOImpl.getInstance().getRecipes();
  }

  @Override public boolean login(String username, String password)
      throws SQLException
  {
    if (!UserDAOImpl.getInstance().doesUserExist(username))
    {
      throw new IllegalAccessError("Username does not exist");
    }
    else
      return UserDAOImpl.getInstance().logInUser(username, password);
  }

  @Override public void register(String user, String password, String email,
      String confirmPassword) throws SQLException, RemoteException
  {
    if (UserDAOImpl.getInstance().doesUserExist(user))
    {
      throw new IllegalAccessError("Username is already taken");
    }
    else
    userList.addUser(UserDAOImpl.getInstance().create(user, password, email));
  }

  @Override public void createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category) throws SQLException
  {

    ArrayList<Integer> ingredientIds = new ArrayList<>();

    //store the ids of the ingredients from the recipe to later add in the relation table
    for (int i = 0; i < ingredients.getSize(); i++)
    {
      ingredientIds.add(IngredientDAOImpl.getInstance().create(ingredients.getIngredient(i).getIngredient(), ingredients.getIngredient(i).getAmount(), ingredients.getIngredient(i).getMeasurement()).getId());
    }

    Recipe recipe = RecipeDAOImpl.getInstance()
        .createRecipe(recipeName, description, ingredients, instructions,
            preparationTime, category);

    for (Integer ingredientId : ingredientIds)
    {
      RecipeDAOImpl.getInstance()
          .addIngredientsToRecipe(recipe.getId(), ingredientId);
    }
  }

  @Override public boolean addListener(GeneralListener<Recipe, Recipe> listener,
      String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Recipe, Recipe> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }
}
