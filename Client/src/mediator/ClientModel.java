package mediator;

import model.ListOfIngredients;
import model.Student;
import utility.observer.subject.LocalSubject;

public interface ClientModel extends LocalSubject<Student, Student>
{
  Student getStudentByStudentNumber(String studyNumber) throws Exception;
  Student getStudentByName(String name) throws Exception;
  void addStudent(Student student) throws Exception;
  void close() throws Exception;
  void createRecipe(String recipeName, ListOfIngredients ingredients, String description);
}
