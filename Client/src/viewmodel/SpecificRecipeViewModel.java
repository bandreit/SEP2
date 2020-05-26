package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LocalModel;
import model.Recipe;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class SpecificRecipeViewModel
{
  private LocalModel model;
  private Recipe recipe;
  private StringProperty recipeName;

  public SpecificRecipeViewModel(LocalModel model)
  {
    this.model = model;
    this.recipeName = new SimpleStringProperty();
  }

  public void setRecipe(int id) throws RemoteException, SQLException
  {
    this.recipe = model.getRecipes().getRecipeById(id);
    recipeName.setValue(recipe.getRecipeName());
  }

  public StringProperty getRecipeNameProperty()
  {
    return recipeName;
  }
}
