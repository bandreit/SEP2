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

public class StudentListClient
    implements ClientModel, RemoteListener<Student, Student>
{
  public static final String HOST = "localhost";
  private String host;
  private PropertyChangeAction<Student, Student> property;
  private LocalModel model;
  private RemoteModel remoteModel;

  public StudentListClient(LocalModel model)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this (model, HOST);
  }

  public StudentListClient(LocalModel model, String host)
      throws RemoteException, NotBoundException, MalformedURLException
  {
    this.model = model;
    this.host = host;
    this.remoteModel = (RemoteModel) Naming
        .lookup("rmi://" + host + ":1099/StudentList");
    UnicastRemoteObject.exportObject(this, 0);
    this.remoteModel.addListener(this);
    this.property = new PropertyChangeProxy<>(this, true);
  }

  @Override public Student getStudentByStudentNumber(String studyNumber)
      throws Exception
  {

    try
    {
      return remoteModel.getStudentByStudentNumber(studyNumber);
    }
    catch (Exception e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public Student getStudentByName(String name) throws Exception
  {
    try
    {
      return remoteModel.getStudentByName(name);
    }
    catch (Exception e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void addStudent(Student student) throws Exception
  {
    try
    {
      remoteModel.addStudent(student);
    }
    catch (Exception e)
    {
      throw new IllegalStateException(getExceptionMessage(e), e);
    }
  }

  @Override public void close() throws Exception
  {
    //idk
  }

  @Override public void createRecipe(String recipeName, ListOfIngredients ingredients, String description)
  {
    Recipe newRecipe=new Recipe(recipeName,ingredients,description);
  }

  @Override public void propertyChange(ObserverEvent<Student, Student> event)
      throws RemoteException
  {
    property.firePropertyChange(event.getPropertyName(), null, event.getValue2());
  }

  @Override public boolean addListener(
      GeneralListener<Student, Student> listener, String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Student, Student> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }

  private String getExceptionMessage(Exception e)
  {
    String message = e.getMessage();
    if (message != null)
    {
      message = message.split(";")[0];
    }
    return message;
  }
}
