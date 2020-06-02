package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.AllRecipesViewModel;
import viewmodel.CreateRecipeViewModel;
import viewmodel.MyRecipesViewModel;
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
  private AllRecipesViewModel allRecipesViewModel;
  private CreateRecipeViewModel createRecipeViewModel;
  private MyRecipesViewModel myRecipesViewModel;

  public MyRecipesViewController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
      throws RemoteException, SQLException
  {
    super.init(viewHandler, viewModels, root);
    allRecipesViewModel = super.getViewModels().getAllRecipesViewModel();
    createRecipeViewModel = super.getViewModels().getCreateRecipeViewModel();
    myRecipesViewModel = super.getViewModels().getMyRecipesViewModel();
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

  public void onEditRecipe() throws RemoteException, SQLException
  {
    try
    {
      createRecipeViewModel.setRecipe(
          myRecipeList.getSelectionModel().getSelectedItem().getIdProperty()
              .get());
      super.getHandler().openView("CreateRecipeView");
    }
    catch (Exception e)
    {
      deleteErrorLabel.setText("No recipe selected");
    }
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
        myRecipesViewModel.removeRecipe(selectedItem.getIdProperty().get());
        myRecipeList.getSelectionModel().clearSelection();
        allRecipesViewModel.removeRecipe(selectedItem.getIdProperty().get());
        myRecipesViewModel.deleteRecipe(selectedItem.getIdProperty().get());
      }
    }
    catch (Exception e)
    {
      deleteErrorLabel.setText("Nothing selected");
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
