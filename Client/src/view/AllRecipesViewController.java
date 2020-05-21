package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public class AllRecipesViewController extends ViewController
{
  @FXML private TableView<RecipeTable> recipeList;
  @FXML private TableColumn<RecipeTable, String> categoryColumn;
  @FXML private TableColumn<RecipeTable, String> recipeColumn;
  @FXML private TableColumn<RecipeTable, String> descriptionColumn;

  public AllRecipesViewController()
  {
    super();
  }
  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
    categoryColumn.setCellValueFactory(cellData->cellData.getValue().getCategoryProperty());
    recipeColumn.setCellValueFactory(cellData->cellData.getValue().getRecipeProperty());
    descriptionColumn.setCellValueFactory(cellData->cellData.getValue().getDescriptionProperty());
    recipeList.setItems(viewModels.getAllRecipesViewModel().getList());
  }

  public void onMyRecipes(ActionEvent actionEvent)
  {
    super.getHandler().openView("MyRecipes");
  }
}
