package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LocalModel;
import model.Recipe;
import view.RecipeTable;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class AllRecipesViewModel
{
  private ObservableList<RecipeTable> list;
  private LocalModel model;

  public AllRecipesViewModel(LocalModel model)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
  }

  public ObservableList<RecipeTable> getList()
  {
    return list;
  }

  public void addRecipe(Recipe recipe)
  {
    list.add(new RecipeTable(recipe));
  }

  public void removeRecipe(String Recipe, String Category)
      throws SQLException, RemoteException
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getRecipeProperty().get().equals(Recipe) && list.get(i)
          .getCategoryProperty().get().equals(Category))
      {
        list.remove(i);
        break;
      }
    }
  }

}
