package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ModelManager implements Model
{
  private RecipeList recipeList;
  private UserList userList;
  private PropertyChangeAction<Recipe, Recipe> property;

  public ModelManager()
  {
    this.recipeList = new RecipeList();
    this.userList = new UserList();
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override public void close()
  {
    property.close();
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
