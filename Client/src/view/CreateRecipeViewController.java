package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;
import viewmodel.CreateRecipeViewModel;
import viewmodel.MyRecipesViewModel;
import viewmodel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Optional;

public class CreateRecipeViewController extends ViewController
{
  @FXML private Label deleteErrorLabel;
  @FXML private TextField ingredientNameField;
  @FXML private ChoiceBox measurementField;
  @FXML private TextField quantityField;
  @FXML private TextField recipeName;
  @FXML private TextArea description;
  @FXML private TextArea instructions;
  @FXML private TextField time;
  @FXML private ChoiceBox category;
  @FXML private TableColumn<CreateRecipeTableRowData, String> ingredientColumn;
  @FXML private TableColumn<CreateRecipeTableRowData, Integer> quantityColumn;
  @FXML private TableColumn<CreateRecipeTableRowData, String> measurementColumn;
  @FXML private TableView<CreateRecipeTableRowData> ingredientsList;
  private CreateRecipeViewModel createRecipeViewModel;
  private MyRecipesViewModel myRecipesViewModel;

  public CreateRecipeViewController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
      throws RemoteException, SQLException
  {
    super.init(viewHandler, viewModels, root);
    createRecipeViewModel = super.getViewModels().getCreateRecipeViewModel();
    myRecipesViewModel = super.getViewModels().getMyRecipesViewModel();
    recipeName.textProperty()
        .bindBidirectional(createRecipeViewModel.getRecipeName());
    description.textProperty()
        .bindBidirectional(createRecipeViewModel.getDescription());
    time.textProperty().bindBidirectional(createRecipeViewModel.getTime());
    instructions.textProperty()
        .bindBidirectional(createRecipeViewModel.getInstructions());
    ingredientNameField.textProperty()
        .bindBidirectional(createRecipeViewModel.getIngredientName());
    deleteErrorLabel.textProperty()
        .bindBidirectional(createRecipeViewModel.getDeleteErrorLabel());
    Bindings.bindBidirectional(quantityField.textProperty(),
        createRecipeViewModel.getQuantity(), new StringConverter<Number>()
        {
          @Override public String toString(Number n)
          {
            if (n == null || n.intValue() == -9)
            {
              return "";
            }
            return n.toString();
          }

          @Override public Number fromString(String s)
          {
            try
            {
              return Integer.parseInt(s);
            }
            catch (Exception e)
            {
              return -9;
            }
          }
        });

    measurementField.valueProperty()
        .bindBidirectional(createRecipeViewModel.getMeasurement());
    category.valueProperty()
        .bindBidirectional(createRecipeViewModel.getCategory());

    ingredientColumn
        .setCellValueFactory(cellData -> cellData.getValue().getIngredient());
    quantityColumn.setCellValueFactory(
        cellData -> cellData.getValue().getQuantity().asObject());
    measurementColumn
        .setCellValueFactory(cellData -> cellData.getValue().getMeasurement());

    ingredientsList.setItems(createRecipeViewModel.getAllIngredients());
  }

  public void onAddIngredient(ActionEvent actionEvent)
  {
    createRecipeViewModel.createIngredient();
  }

  public void onCreate(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    if (createRecipeViewModel.validateRecipeFields())
    {
      if (createRecipeViewModel.getIsEditing())
      {
        myRecipesViewModel.editRecipe(createRecipeViewModel.editRecipe());
      }
      else
      {
        myRecipesViewModel.addRecipe(createRecipeViewModel.createRecipe());
      }

      createRecipeViewModel.clear();
      super.getHandler().openView("AllRecipes");
    }
  }

  public void onCancel(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    super.getHandler().openView("AllRecipes");
  }

  @FXML private void removeIngredientButtonPressed()
  {
    deleteErrorLabel.setText("");
    try
    {
      CreateRecipeTableRowData selectedItem = ingredientsList
          .getSelectionModel().getSelectedItem();
      boolean remove = confirmation();
      if (remove)
      {
        createRecipeViewModel.remove(selectedItem.getIngredient().get());
        ingredientsList.getSelectionModel().clearSelection();
        getViewModels().getCreateRecipeViewModel()
            .remove(selectedItem.getIngredient().get());
      }
    }
    catch (Exception e)
    {
      deleteErrorLabel.setText("Item not found: " + e.getMessage());
    }
  }

  private boolean confirmation()
  {
    int index = ingredientsList.getSelectionModel().getSelectedIndex();
    CreateRecipeTableRowData selectedItem = ingredientsList.getItems()
        .get(index);
    if (index < 0 || index >= ingredientsList.getItems().size())
    {
      return false;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(
        "Removing ingredient {" + selectedItem.getIngredient().get() + "}");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }
}
