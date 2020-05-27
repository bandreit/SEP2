package view;

import com.sun.javafx.scene.control.InputField;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import org.w3c.dom.Text;
import viewmodel.ViewModelFactory;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AllRecipesViewController extends ViewController
{
  @FXML private ChoiceBox filter;
  @FXML private TextField searchString;
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
    categoryColumn.setCellValueFactory(
        cellData -> cellData.getValue().getCategoryProperty());
    recipeColumn.setCellValueFactory(
        cellData -> cellData.getValue().getRecipeProperty());
    descriptionColumn.setCellValueFactory(
        cellData -> cellData.getValue().getDescriptionProperty());
    searchString.textProperty().bindBidirectional(
        super.getViewModels().getAllRecipesViewModel()
            .getSearchStringProperty());
    recipeList.setItems(viewModels.getAllRecipesViewModel().getList());
    recipeList.setOnMouseClicked((MouseEvent event) -> {
      if (event.getButton().equals(MouseButton.PRIMARY)
          && event.getClickCount() == 2)
      {
        try
        {
          super.getHandler().openView("SpecificRecipe");
          super.getViewModels().getSpecificRecipeViewModel().setRecipe(recipeList.getSelectionModel().getSelectedItem().getIdProperty().get());
        }
        catch (RemoteException | SQLException e)
        {
          e.printStackTrace();
        }
      }
    });
    filter.valueProperty().bindBidirectional(
        super.getViewModels().getAllRecipesViewModel().getFilter());
  }

  public void onMyRecipes(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    super.getHandler().openView("MyRecipes");
  }

  public void searchRecipes(KeyEvent keyEvent)
      throws RemoteException, SQLException
  {
    if (keyEvent.getCode() == KeyCode.ENTER)
    {
      super.getViewModels().getAllRecipesViewModel().searchRecipes();
    }
  }
}

