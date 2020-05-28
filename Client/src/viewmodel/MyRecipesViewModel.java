package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.*;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.CreateRecipeTableRowData;
import view.RecipeTable;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Optional;

public class MyRecipesViewModel
{
  private LocalModel model;
  private ObservableList<RecipeTable> list;
  private StringProperty deleteErrorLabel;

  public MyRecipesViewModel(LocalModel model)
      throws RemoteException, SQLException
  {
    this.model = model;
    this.deleteErrorLabel = new SimpleStringProperty();
    this.list = FXCollections.observableArrayList();
  }

  public StringProperty getDeleteErrorLabel()
  {
    return deleteErrorLabel;
  }

  public ObservableList<RecipeTable> getList()
      throws RemoteException, SQLException
  {
    RecipeList recipes = model.getRecipesForUser();
    for (int i = 0; i < recipes.getSize(); i++)
    {
      list.add(new RecipeTable(recipes.getRecipe(i)));
    }
    return list;
  }

  public void addRecipe(Recipe recipe)
  {
    list.add(new RecipeTable(recipe));
  }

  public void editRecipe(Recipe recipe) {
    int recipeIndex = -1;

    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getIdProperty().equals(recipe.getId())) {
        recipeIndex = i;
        break;
      }
    }

    if(recipeIndex > -1) {
      list.set(recipeIndex, new RecipeTable(recipe));
    }
  }

  public void removeRecipe(int id)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getIdProperty().get() == id)
      {
        list.remove(i);
        break;
      }
    }
  }

  public void deleteRecipe(int id) throws SQLException, RemoteException
  {
    model.deleteRecipe(id);
  }
}
