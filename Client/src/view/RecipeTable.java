package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Recipe;

public class RecipeTable
{
  private StringProperty category;
  private StringProperty recipes;

  public RecipeTable(Recipe recipe)
  {
    this.category=new SimpleStringProperty(recipe.getCategory());
    this.recipes=new SimpleStringProperty(recipe.getRecipeName());
  }
  public StringProperty getCategoryProperty()
  {
    return category;
  }

  public StringProperty getRecipeProperty()
  {
    return recipes;
  }
}
