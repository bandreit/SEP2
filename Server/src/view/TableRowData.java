package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Recipe;

public class TableRowData
{
  private StringProperty nameProperty;
  private StringProperty numberProperty;

  public TableRowData(Recipe recipe)
  {
    this.nameProperty = new SimpleStringProperty("");
    this.numberProperty = new SimpleStringProperty("");
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
