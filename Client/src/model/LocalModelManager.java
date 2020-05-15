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
  private PropertyChangeAction<String, String> property;
  private boolean loggedIn;

  public LocalModelManager() throws IOException
  {
    try
    {
      this.loggedIn = false;
      clientModel = new Client(this);
      clientModel.addListener(this);
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

  @Override public void close(Recipe recipe)
  {
    //hz
  }
}
