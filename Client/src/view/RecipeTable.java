package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Recipe;

public class RecipeTable
{
  private StringProperty category;
  private StringProperty recipes;
  private StringProperty description;

  public RecipeTable(Recipe recipe)
  {
    this.category=new SimpleStringProperty(recipe.getCategory());
    this.recipes=new SimpleStringProperty(recipe.getRecipeName());
    this.description=new SimpleStringProperty(recipe.getDescription());
  }
  public StringProperty getCategoryProperty()
  {
    return category;
  }

  public StringProperty getRecipeProperty()
  {
    return recipes;
  }

  public StringProperty getDescriptionProperty()
  {
    return description;
  }
}
