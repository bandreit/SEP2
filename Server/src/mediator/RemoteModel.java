package mediator;

import model.Recipe;
import utility.observer.subject.RemoteSubject;

import java.rmi.Remote;

public interface RemoteModel extends RemoteSubject<Recipe, Recipe>, Remote
{
  Recipe getStudentByStudentNumber(String studyNumber) throws Exception;
  Recipe getStudentByName(String name) throws Exception;
  void addStudent(Recipe recipe) throws Exception;
  void close() throws Exception;

}
