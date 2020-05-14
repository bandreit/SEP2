package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Student;

public class TableRowData
{
  private StringProperty nameProperty;
  private StringProperty numberProperty;

  public TableRowData(Student student)
  {
    this.nameProperty = new SimpleStringProperty(student.getName());
    this.numberProperty = new SimpleStringProperty(student.getStudyNumber());
  }

  public StringProperty getNameProperty()
  {
    return nameProperty;
  }

  public StringProperty getNumberProperty()
  {
    return numberProperty;
  }

}
