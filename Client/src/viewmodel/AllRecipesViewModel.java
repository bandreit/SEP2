package viewmodel;

import javafx.application.Platform;
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
    this.model=model;
    list= FXCollections.observableArrayList();
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
}
