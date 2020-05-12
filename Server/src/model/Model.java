package model;

import utility.observer.subject.LocalSubject;

import java.rmi.RemoteException;

public interface Model extends LocalSubject<Recipe, Recipe>
{
  public Recipe getStudentByStudyNumber(String studyNumber) throws Exception,
      RemoteException;
  public Recipe getStudentByName(String name) throws Exception, RemoteException;
  public void addStudent(Recipe recipe) throws Exception, RemoteException;
  int getStudentListSize() throws Exception, RemoteException;
  Recipe getStudent(int index) throws Exception, RemoteException;
  public void close();
}
