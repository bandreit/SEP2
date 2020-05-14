package model;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.rmi.RemoteException;

public class ModelManager implements Model
{
  private RecipeList recipeList;
  private PropertyChangeAction<Recipe, Recipe> property;

  public ModelManager()
  {
    this.recipeList = new RecipeList();
    this.property = new PropertyChangeProxy<>(this);
  }

  @Override
  public Recipe getStudentByStudyNumber(String studyNumber) throws IllegalArgumentException,
      RemoteException
  {
    return recipeList.getStudentByNumber(studyNumber);
  }

  @Override
  public Recipe getStudentByName(String name) throws IllegalArgumentException, RemoteException
  {
    return recipeList.getStudentByName(name);
  }

  @Override public void addStudent(Recipe recipe) throws IllegalArgumentException, RemoteException
  {
    recipeList.addStudent(recipe);
    property.firePropertyChange("add", null, recipe);
  }

  @Override public int getStudentListSize() throws Exception, RemoteException
  {
    return recipeList.getSize();
  }

  @Override public Recipe getStudent(int index) throws Exception, RemoteException
  {
    return recipeList.getStudent(index);
  }

  @Override public void close()
  {
    property.close();
  }

  @Override public boolean login(String username, String password)
  {
   //database
    return true;
  }

  @Override public boolean register(String user, String password, String email,
      String confirmPassword)
  {
    //adding to database
    return true;
  }

  @Override public boolean addListener(
      GeneralListener<Recipe, Recipe> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Recipe, Recipe> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }
}
