package model;

import utility.observer.subject.LocalSubject;

public interface LocalModel extends LocalSubject<String, String>
{
  Student getStudentByStudyNumber(String studyNumber) throws Exception;
  Student getStudentByName(String name) throws Exception;
  void addStudent(Student student) throws Exception;
  void close(Student student);

  void createRecipe(String recipeName, ListOfIngredients ingredients, String description);
  void addAmount(String s);
  void addIngredient(String s);
  ListOfIngredients getListOfIngredients();
}
