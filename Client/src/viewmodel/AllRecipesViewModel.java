package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.LocalModel;
import model.Recipe;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.RecipeTable;

public class AllRecipesViewModel implements LocalListener<Recipe, Recipe>
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

  @Override public void propertyChange(ObserverEvent<Recipe, Recipe> event)
  {
    Platform.runLater(() -> {
      list.add(new RecipeTable(event.getValue2()));
    });
  }
}
