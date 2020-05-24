package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Ingredient;
import model.LocalModel;
import model.Recipe;
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
  {
    this.model=model;
    list= FXCollections.observableArrayList();
    this.deleteErrorLabel = new SimpleStringProperty();
  }

  public StringProperty getDeleteErrorLabel()
  {
    return deleteErrorLabel;
  }

  public ObservableList<RecipeTable> getList()
  {
    return list;
  }
  public void addRecipe(Recipe recipe)
  {
    list.add(new RecipeTable(recipe));
  }

  public void removeRecipe(String Recipe,String Category)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getRecipeProperty().get().equals(Recipe) && list.get(i).getCategoryProperty().get().equals(Category) )
      {
        list.remove(i);
        break;
      }
    }
  }
  public void deleteRecipe()
      throws SQLException, RemoteException
  {
      model.deleteRecipe(list.get(1).getRecipeProperty().get(),list.get(0).getCategoryProperty().get());//ggwp naxui you won
  }
}
