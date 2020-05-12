package mediator;

import model.Recipe;
import utility.observer.subject.LocalSubject;

public interface ClientModel extends LocalSubject<Recipe, Recipe>
{
  Recipe getStudentByStudentNumber(String studyNumber) throws Exception;
  Recipe getStudentByName(String name) throws Exception;
  void addStudent(Recipe recipe) throws Exception;
  void close() throws Exception;
}
