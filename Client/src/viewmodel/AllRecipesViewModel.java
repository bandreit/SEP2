package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ingredient;
import model.LocalModel;
import model.Recipe;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.RecipeTable;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class AllRecipesViewModel implements LocalListener<Recipe, Ingredient>
{
  private ObservableList<RecipeTable> list;
  private LocalModel model;

  public AllRecipesViewModel(LocalModel model)
  {
    this.model = model;
    list = FXCollections.observableArrayList();
    this.model.addListener(this,"ADD");
  }

  public ObservableList<RecipeTable> getList()
      throws RemoteException, SQLException
  {
    for(int i=0;i<model.getRecipes().getSize();i++)
    {
      list.add(new RecipeTable(model.getRecipes().getRecipe(i)));
    }
    return list;
  }

  public void addRecipe(Recipe recipe)
  {
    list.add(new RecipeTable(recipe));
  }

  public void removeRecipe(int id)
      throws SQLException, RemoteException
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

  @Override public void propertyChange(ObserverEvent<Recipe, Ingredient> event)
  {
    Platform.runLater(() -> {
      list.removeAll();
      try
      {
        getList();
      }
      catch (RemoteException | SQLException e)
      {
        e.printStackTrace();
      }
    });
  }
}
