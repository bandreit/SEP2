package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface Model extends LocalSubject<Recipe, Recipe>
{
  boolean login(String username,String password) throws SQLException;
  void register(String user, String password,String email,String confirmPassword)
      throws SQLException, RemoteException;
  public void close();
}
