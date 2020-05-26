package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ingredient;
import model.LocalModel;
import model.Recipe;
import model.RecipeList;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.RecipeTable;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class AllRecipesViewModel implements LocalListener<Recipe, Ingredient>
{
  private ObservableList<RecipeTable> list;
  private LocalModel model;
  private StringProperty filter;
  private StringProperty searchString;

  public AllRecipesViewModel(LocalModel model)
  {
    this.model = model;
    this.filter = new SimpleStringProperty("All");
    this.searchString = new SimpleStringProperty();
    list = FXCollections.observableArrayList();
    this.model.addListener(this, "ADD");
    filter.addListener((obs, olV, newV) -> {
      try
      {
        filterRecipesByCategory();
      }
      catch (RemoteException | SQLException e)
      {
        e.printStackTrace();
      }
    });
  }

  public StringProperty getFilter()
  {
    return filter;
  }

  public StringProperty getSearchStringProperty()
  {
    return searchString;
  }

  public ObservableList<RecipeTable> getList()
      throws RemoteException, SQLException
  {
    RecipeList recipes = model.getRecipes();
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

  public void removeRecipe(int id) throws SQLException, RemoteException
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

  public void filterRecipesByCategory() throws RemoteException, SQLException
  {
    RecipeList recipes = model.getRecipes();
    list.clear();
    for (int i = 0; i < recipes.getSize(); i++)
    {
      if (filter.get().equals(recipes.getRecipe(i).getCategory()))
      {
        list.add(new RecipeTable(recipes.getRecipe(i)));
      }
      if (filter.get().equals("All"))
      {
        list.add(new RecipeTable(recipes.getRecipe(i)));
      }
    }
  }

  public void searchRecipes() throws RemoteException, SQLException
  {
    RecipeList recipes;
    if (searchString.get() == null)
    {
      recipes = model.getRecipes();
    }
    else
    {
      recipes = model.searchRecipes(searchString.get());
    }
    list.clear();
    for (int i = 0; i < recipes.getSize(); i++)
    {
      list.add(new RecipeTable(recipes.getRecipe(i)));
    }
  }

  @Override public void propertyChange(ObserverEvent<Recipe, Ingredient> event)
  {
    Platform.runLater(() -> {
      list.clear();
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
