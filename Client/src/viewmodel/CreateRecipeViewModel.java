package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ListOfIngredients;
import model.LocalModel;


public class CreateRecipeViewModel
{
  private LocalModel model;
  private StringProperty recipeName;
  private StringProperty description;
  private ListOfIngredients ingredients;

  public CreateRecipeViewModel(LocalModel model)
  {
    this.model = model;
    this.recipeName=new SimpleStringProperty();
    this.description=new SimpleStringProperty();
  }

  public StringProperty getRecipeName()
  {
    return recipeName;
  }

  public StringProperty getDescription()
  {
    return description;
  }

  public void createRecipe()
  {
    model.createRecipe(recipeName.get(),model.getListOfIngredients(),description.get());
  }

}
