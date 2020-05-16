package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LocalModel;

public class IngredientsViewModel
{
  private StringProperty ingredients;
  private LocalModel model;

  public IngredientsViewModel(LocalModel model)
  {
    this.model = model;
    this.ingredients=new SimpleStringProperty();
  }
  public StringProperty getIngredients()
  {
    return ingredients;
  }
}
