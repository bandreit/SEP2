package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ListOfIngredients;
import model.LocalModel;
import model.Recipe;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class SpecificRecipeViewModel
{
  private LocalModel model;
  private Recipe recipe;
  private StringProperty recipeName;
  private StringProperty category;
  private StringProperty time;
  private StringProperty description;
  private StringProperty ingredients;
  private StringProperty directions;

  public SpecificRecipeViewModel(LocalModel model)
  {
    this.model = model;
    this.recipeName = new SimpleStringProperty();
    this.category = new SimpleStringProperty();
    this.time = new SimpleStringProperty();
    this.description = new SimpleStringProperty();
    this.ingredients = new SimpleStringProperty();
    this.directions = new SimpleStringProperty();
  }

  public void setRecipe(int id) throws RemoteException, SQLException
  {
    this.recipe = model.getRecipes().getRecipeById(id);
    recipeName.setValue(recipe.getRecipeName());
    category.setValue(recipe.getCategory());
    time.setValue(Integer.toString(recipe.getPreparationTime()));
    description.setValue(recipe.getDescription());
    directions.setValue(recipe.getInstructions());
    setIngredients();
  }

  private void setIngredients() throws SQLException, RemoteException
  {
    ListOfIngredients ingredients = model
        .getIngredientsForRecipe(recipe.getId());
    String ingredientsText = "";
    for (int i = 0; i < ingredients.getSize(); i++)
    {
      ingredientsText +=
          "•" + ingredients.getIngredient(i).getAmount() + " " + ingredients
              .getIngredient(i).getMeasurement() + " " + ingredients
              .getIngredient(i).getIngredient() + "\n";
    }
    this.ingredients.setValue(ingredientsText);
  }

  public StringProperty getRecipeNameProperty()
  {
    return recipeName;
  }

  public StringProperty getCategoryProperty()
  {
    return category;
  }

  public StringProperty getTimeProperty()
  {
    return time;
  }

  public StringProperty getDescriptionProperty()
  {
    return description;
  }

  public StringProperty getIngredientsProperty()
  {
    return ingredients;
  }

  public StringProperty getDirectionsProperty()
  {
    return directions;
  }
}
