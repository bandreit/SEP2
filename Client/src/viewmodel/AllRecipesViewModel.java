package viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LocalModel;
import model.Recipe;
import view.RecipeTable;

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
  {
    return list;
  }
  public void addToTheList(Recipe recipe)
  {
    list.addAll(new RecipeTable(recipe));
  }
}
