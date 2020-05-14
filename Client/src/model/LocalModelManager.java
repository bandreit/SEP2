package model;

import mediator.ClientModel;
import mediator.StudentListClient;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.io.IOException;

public class LocalModelManager implements LocalModel, LocalListener<Student, Student>
{
  private ClientModel clientModel;
  private PropertyChangeAction<String, String> property;
  private String amount;
  private String ingredient;

  public LocalModelManager() throws IOException
  {
    try
    {
      clientModel = new StudentListClient(this);
      clientModel.addListener(this);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    property = new PropertyChangeProxy<>(this, true);
  }

  @Override public Student getStudentByStudyNumber(String studyNumber)
      throws Exception
  {
    return clientModel.getStudentByStudentNumber(studyNumber);
  }

  @Override public Student getStudentByName(String name) throws Exception
  {
    return clientModel.getStudentByName(name);
  }

  @Override public void createRecipe(String recipeName,
      ListOfIngredients ingredients, String description)
  {

  }

  @Override public void addAmount(String s)
  {
    this.amount=s;
  }

  @Override public void addIngredient(String s)
  {
    this.ingredient=s;
  }


  @Override public ListOfIngredients getListOfIngredients()
  {
    ListOfIngredients ingredients=new ListOfIngredients();
    Ingredient ing=new Ingredient(ingredient,amount);
    ingredients.addIngredient(ing);
    return ingredients;
  }

  @Override public void addStudent(Student student) throws Exception
  {
    clientModel.addStudent(student);
  }

  @Override public void close(Student student)
  {
    //hz
  }


  @Override public void propertyChange(ObserverEvent<Student, Student> event)
  {
    String message = "Message: Added " + event.getValue2();
    property.firePropertyChange("broadcast", null, message);
  }

  @Override public boolean addListener(GeneralListener<String, String> listener,
      String... propertyNames)
  {
    return property.addListener(listener, propertyNames);
  }

  @Override public boolean removeListener(
      GeneralListener<String, String> listener, String... propertyNames)
  {
    return property.removeListener(listener, propertyNames);
  }
}
