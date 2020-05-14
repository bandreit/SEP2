package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Student;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.TableRowData;

import java.util.ArrayList;

public class ServerViewModel implements LocalListener<Student, Student>
{
  private Model model;
  private StringProperty nameField;
  private StringProperty numberField;
  private ObservableList<TableRowData> list;

  public ServerViewModel(Model model) throws Exception
  {
    this.model = model;
    this.model.addListener(this, "add");
    this.list = createList();
    this.nameField = new SimpleStringProperty();
    this.numberField = new SimpleStringProperty();
  }

  public StringProperty getNameFieldProperty()
  {
    return nameField;
  }

  public StringProperty getNumberFieldProperty()
  {
    return numberField;
  }

  public ObservableList<TableRowData> getList()
  {
    return list;
  }

  private synchronized ObservableList<TableRowData> createList()
      throws Exception
  {
    ObservableList<TableRowData> obsList = FXCollections.observableArrayList();

    ArrayList<Student> students = new ArrayList<>();
    for (int i = 0; i < model.getStudentListSize(); i++)
    {
      students.add(model.getStudent(i));
    }
    for (int i = 0; i < students.size(); i++)
    {
      obsList.add(new TableRowData(students.get(i)));
    }
    return obsList;
  }

  public void setMessage(String inputField)
  {
    //    model.addMessage(inputField);
  }


  private void addToTheList(Student student)
  {
    list.add(new TableRowData(student));
  }

  public void addStudent()
  {
    try
    {
      model.addStudent(new Student(nameField.get(), numberField.get()));
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  @Override public void propertyChange(ObserverEvent<Student, Student> event)
  {
    Platform.runLater(() -> {
      addToTheList(event.getValue2());
    });
  }
}
