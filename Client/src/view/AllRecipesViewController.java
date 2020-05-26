package view;

import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AllRecipesViewController extends ViewController
{
  @FXML private ChoiceBox filter;
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
      throws RemoteException, SQLException
  {
    super.init(viewHandler, viewModels, root);
    categoryColumn.setCellValueFactory(cellData->cellData.getValue().getCategoryProperty());
    recipeColumn.setCellValueFactory(cellData->cellData.getValue().getRecipeProperty());
    descriptionColumn.setCellValueFactory(cellData->cellData.getValue().getDescriptionProperty());
    recipeList.setItems(viewModels.getAllRecipesViewModel().getList());
    filter.valueProperty().bindBidirectional(
        super.getViewModels().getAllRecipesViewModel().getFilter());
  }

  public void onMyRecipes(ActionEvent actionEvent)
  {
    super.getHandler().openView("MyRecipes");
  }

  public void searchByCategory(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    super.getViewModels().getAllRecipesViewModel().filterRecipesByCategory();
  }
}

