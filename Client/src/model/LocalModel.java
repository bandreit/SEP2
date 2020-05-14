package model;

import utility.observer.subject.LocalSubject;

public interface LocalModel extends LocalSubject<String, String>
{
  Recipe getStudentByStudyNumber(String studyNumber) throws Exception;
  Recipe getStudentByName(String name) throws Exception;
  void addStudent(Recipe recipe) throws Exception;
  void close(Recipe recipe);
  boolean login(String user, String password);
  boolean isLoggedIn();
  boolean register(String user, String password,String email,String confirmPassword);


}
