package mediator;

import model.Recipe;
import utility.observer.subject.LocalSubject;

public interface ClientModel extends LocalSubject<Recipe, Recipe>
{
  Recipe getStudentByStudentNumber(String studyNumber) throws Exception;
  Recipe getStudentByName(String name) throws Exception;
  void addStudent(Recipe recipe) throws Exception;
  boolean login(String username,String password);
  void close() throws Exception;
  boolean register(String user, String password,String email,String confirmPassword);
}
