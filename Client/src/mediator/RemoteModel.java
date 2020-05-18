package mediator;

import model.Recipe;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface RemoteModel extends RemoteSubject<Recipe, Recipe>, Remote
{
  boolean login(String username, String password) throws RemoteException, SQLException;
  void register(String user, String password, String email,
      String confirmPassword) throws RemoteException, SQLException;
}
