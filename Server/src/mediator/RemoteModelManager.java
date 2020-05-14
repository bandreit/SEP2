package mediator;

import model.Model;
import model.Student;
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

public class RemoteModelManager implements RemoteModel, LocalListener<Student, Student>
{
  private PropertyChangeAction<Student, Student> property;
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
    Naming.rebind("StudentList", this);
    System.out.println("Server started...");
  }

  @Override public Student getStudentByStudentNumber(String studyNumber)
      throws Exception, RemoteException
  {
    return model.getStudentByStudyNumber(studyNumber);
  }

  @Override public Student getStudentByName(String name) throws Exception, RemoteException
  {
    return model.getStudentByName(name);
  }

  @Override public void addStudent(Student student) throws Exception, RemoteException
  {
    model.addStudent(student);
//    property.firePropertyChange("add", null, student);
  }

  @Override public boolean addListener(
      GeneralListener<Student, Student> listener, String... propertyNames)
      throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<Student, Student> listener, String... propertyNames)
      throws RemoteException
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public void propertyChange(ObserverEvent<Student, Student> event)
  {
    property.firePropertyChange(event.getPropertyName(), null, event.getValue2());
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
