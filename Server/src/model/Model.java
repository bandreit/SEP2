package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;
import java.sql.SQLException;

public interface Model extends LocalSubject<Recipe, Recipe>
{
  public Recipe getStudentByStudyNumber(String studyNumber) throws Exception,
      RemoteException;
  public Recipe getStudentByName(String name) throws Exception, RemoteException;
  public void addStudent(Recipe recipe) throws Exception, RemoteException;
  int getStudentListSize() throws Exception, RemoteException;
  Recipe getStudent(int index) throws Exception, RemoteException;
  public void close();
  boolean login(String username,String password);
  boolean register(String user, String password,String email,String confirmPassword)
      throws SQLException;
}
