package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Optional;

public class SpecificRecipeViewModel
    implements LocalListener<Recipe, Ingredient>
{
  private LocalModel model;
  private Recipe recipe;
  private StringProperty recipeName;
  private StringProperty category;
  private StringProperty time;
  private StringProperty description;
  private StringProperty ingredients;
  private StringProperty directions;
  private StringProperty writeComment;
  private StringProperty comments;

  public SpecificRecipeViewModel(LocalModel model)
  {
    this.model = model;
    this.recipeName = new SimpleStringProperty();
    this.category = new SimpleStringProperty();
    this.time = new SimpleStringProperty();
    this.description = new SimpleStringProperty();
    this.ingredients = new SimpleStringProperty();
    this.directions = new SimpleStringProperty();
    this.writeComment = new SimpleStringProperty();
    this.comments = new SimpleStringProperty();
    this.model.addListener(this, "Comment");

  }

  public void setRecipe(int id) throws RemoteException, SQLException
  {
    this.recipe = model.getRecipes().getRecipeById(id);
    recipeName.setValue(recipe.getRecipeName());
    category.setValue(recipe.getCategory());
    time.setValue(recipe.getPreparationTime() + " m");
    description.setValue(" '' " + recipe.getDescription() + " '' ");
    directions.setValue(recipe.getInstructions());
    setIngredients();
    updateComments();
  }

  public void setComment() throws RemoteException, SQLException
  {
    if (writeComment.get() != null)
    {
      model.createComment(recipe.getId(), User.getInstance().getUserID(),
          writeComment.get());
      writeComment.setValue("");
      updateComments();
    }
  }

  private void setIngredients() throws SQLException, RemoteException
  {
    ListOfIngredients ingredients = model
        .getIngredientsForRecipe(recipe.getId());
    String ingredientsText = "";
    for (int i = 0; i < ingredients.getSize(); i++)
    {
      ingredientsText +=
          "??? " + ingredients.getIngredient(i).getAmount() + " " + ingredients
              .getIngredient(i).getMeasurement() + " " + ingredients
              .getIngredient(i).getIngredient() + "\n";
    }
    this.ingredients.setValue(ingredientsText);
  }

  private void updateComments() throws SQLException, RemoteException
  {
    this.comments.setValue(model.getComment(recipe.getId()));
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

  public StringProperty getWriteComment()
  {
    return writeComment;
  }

  public StringProperty getComments()
  {
    return comments;
  }

  private boolean confirmation()
  {
    RecipeList savedRecipeList = model.getSavedRecipeList();
    for (int i = 0; i < savedRecipeList.getSize(); i++)
    {
      if (savedRecipeList.getRecipe(i).getOwnerId() == recipe.getOwnerId())
      {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Somebody commented under your comment");
        alert.setHeaderText(
            "Somebody commented on " + recipe.getId() + recipe.getRecipeName());
        Optional<ButtonType> result = alert.showAndWait();
        return (result.isPresent()) && (result.get() == ButtonType.OK);
      }
    }
    return false;
  }

  @Override public void propertyChange(ObserverEvent<Recipe, Ingredient> event)
  {
    Platform.runLater(() -> {
      confirmation();
    });
  }

}

