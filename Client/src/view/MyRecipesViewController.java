package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Optional;

public class MyRecipesViewController extends ViewController
{
  @FXML private Label deleteErrorLabel;
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
    categoryColumn.setCellValueFactory(
        cellData -> cellData.getValue().getCategoryProperty());
    recipeColumn.setCellValueFactory(
        cellData -> cellData.getValue().getRecipeProperty());
    myRecipeList.setItems(viewModels.getMyRecipesViewModel().getList());
    deleteErrorLabel.textProperty().bindBidirectional(
        super.getViewModels().getMyRecipesViewModel().getDeleteErrorLabel());
  }
  public void onAddRecipe(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    super.getHandler().openView("CreateRecipeView");
  }

  public void backToAllRecipes(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    super.getHandler().openView("AllRecipes");
  }

  public void removeRecipeButtonPressed()
  {
    deleteErrorLabel.setText("");
    try
    {
      RecipeTable selectedItem = myRecipeList.getSelectionModel()
          .getSelectedItem();
      boolean remove = confirmation();
      if (remove)
      {
        super.getViewModels().getMyRecipesViewModel().removeRecipe(selectedItem.getIdProperty().get());
        myRecipeList.getSelectionModel().clearSelection();
        super.getViewModels().getAllRecipesViewModel().removeRecipe(selectedItem.getIdProperty().get());
        super.getViewModels().getMyRecipesViewModel().deleteRecipe(selectedItem.getIdProperty().get());
      }
    }
    catch (Exception e)
    {
      deleteErrorLabel.setText("Item not found: " + e.getMessage());
    }
  }

  private boolean confirmation()
  {
    int index = myRecipeList.getSelectionModel().getSelectedIndex();
    RecipeTable selectedItem = myRecipeList.getItems().get(index);
    if (index < 0 || index >= myRecipeList.getItems().size())
    {
      return false;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Removing Recipe {" + "Name: " + selectedItem.getRecipeProperty().get()
            + " Category: " + selectedItem.getCategoryProperty().get() + "}");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

}
