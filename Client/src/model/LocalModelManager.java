package model;

import mediator.ClientModel;
import mediator.StudentListClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.io.IOException;

public class LocalModelManager implements LocalModel, LocalListener<Recipe, Recipe>
{
  private ClientModel clientModel;
  private PropertyChangeAction<String, String> property;
  private boolean loggedIn;

  public LocalModelManager() throws IOException
  {
    try
    {
      this.loggedIn = false;
      clientModel = new StudentListClient(this);
      clientModel.addListener(this);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    property = new PropertyChangeProxy<>(this, true);
  }

  @Override public Recipe getStudentByStudyNumber(String studyNumber)
      throws Exception
  {
    return clientModel.getStudentByStudentNumber(studyNumber);
  }

  @Override public Recipe getStudentByName(String name) throws Exception
  {
    return clientModel.getStudentByName(name);
  }

  @Override public void addStudent(Recipe recipe) throws Exception
  {
    clientModel.addStudent(recipe);
  }

  @Override public void close(Recipe recipe)
  {
    //hz
  }

  @Override public void login(String user, String password)
  {
    validateLogin(user, password);
    loggedIn = true;
  }

  private void validateLogin(String user, String password)
  {
    if (user == null || user.isEmpty())
    {
      throw new IllegalArgumentException("Username cannot be empty");
    }
    if (password == null || password.length() < 6)
    {
      throw new IllegalArgumentException("Password must contain at least 6 characters");
    }
  }

  @Override public boolean isLoggedIn()
  {
    return loggedIn;
  }

  @Override public void propertyChange(ObserverEvent<Recipe, Recipe> event)
  {
    String message = "Message: Added " + event.getValue2();
    property.firePropertyChange("broadcast", null, message);
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }



}
