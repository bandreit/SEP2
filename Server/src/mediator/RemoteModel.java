package mediator;

import model.Recipe;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface RemoteModel extends RemoteSubject<Recipe, Recipe>, Remote
{
  Recipe getStudentByStudentNumber(String studyNumber) throws RemoteException,
      Exception;
  Recipe getStudentByName(String name) throws RemoteException, Exception;
  void addStudent(Recipe recipe) throws RemoteException, Exception;
  void close() throws RemoteException;
  boolean login(String username, String password) throws RemoteException;
  boolean register(String user, String password, String email,
      String confirmPassword) throws RemoteException, SQLException;

}
