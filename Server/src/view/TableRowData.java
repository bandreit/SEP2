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
    this.nameProperty = new SimpleStringProperty(recipe.getName());
    this.numberProperty = new SimpleStringProperty(recipe.getStudyNumber());
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
