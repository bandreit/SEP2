package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class MyRecipesViewController extends ViewController
{
  @FXML private TableView<RecipeTable> myRecipeList;
  @FXML private TableColumn<RecipeTable, String> categoryColumn;
  @FXML private TableColumn<RecipeTable, String> recipeColumn;
  public MyRecipesViewController()
  {
    super();
  }
  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
      throws RemoteException, SQLException
  {
    super.init(viewHandler, viewModels, root);
    categoryColumn.setCellValueFactory(cellData->cellData.getValue().getCategoryProperty());
    recipeColumn.setCellValueFactory(cellData->cellData.getValue().getRecipeProperty());
    myRecipeList.setItems(viewModels.getMyRecipesViewModel().getList());
  }
  public void onAddRecipe(ActionEvent actionEvent)
  {
    super.getHandler().openView("CreateRecipeView");
  }

  public void backToAllRecipes(ActionEvent actionEvent)
  {
    super.getHandler().openView("AllRecipes");
  }
}
