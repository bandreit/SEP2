package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Recipe;
import model.RecipeList;

public class RecipeTable
{
  private IntegerProperty id;
  private StringProperty category;
  private StringProperty recipes;

  public RecipeTable(Recipe recipe)
  {
    this.id = new SimpleIntegerProperty(recipe.getId());
    this.category=new SimpleStringProperty(recipe.getCategory() + "\n" + recipe.getDescription());
    this.recipes=new SimpleStringProperty(recipe.getRecipeName());
  }

  public IntegerProperty getIdProperty() { return id;}

  public StringProperty getCategoryProperty()
  {
    return category;
  }

  public StringProperty getRecipeProperty()
  {
    return recipes;
  }

}
