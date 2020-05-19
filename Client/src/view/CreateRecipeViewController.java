package view;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import viewmodel.CreateRecipeViewModel;
import viewmodel.ViewModelFactory;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.rmi.RemoteException;
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
  @FXML private TableColumn<CreateRecipeTableRowData, String> quantityColumn;
  @FXML private TableColumn<CreateRecipeTableRowData, String> measurementColumn;
  @FXML private TableView<CreateRecipeTableRowData> ingredientsList;

  public CreateRecipeViewController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
    recipeName.textProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getRecipeName());
    description.textProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getDescription());
    time.textProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getTime());
    instructions.textProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getInstructions());
    ingredientNameField.textProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getIngredientName());
    quantityField.textProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getQuantity());
    deleteErrorLabel.textProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getDeleteErrorLabel());

    measurementField.valueProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getMeasurement());
    category.valueProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getCategory());

    ingredientColumn
        .setCellValueFactory(cellData -> cellData.getValue().getIngredient());
    quantityColumn
        .setCellValueFactory(cellData -> cellData.getValue().getQuantity());
    measurementColumn
        .setCellValueFactory(cellData -> cellData.getValue().getMeasurement());

    ingredientsList.setItems(
        super.getViewModels().getCreateRecipeViewModel().getAllIngredients());
  }

  public void onAddIngredient(ActionEvent actionEvent)
  {
    super.getViewModels().getCreateRecipeViewModel().createIngredient();
  }

  public void onCreate(ActionEvent actionEvent) throws RemoteException
  {
    super.getViewModels().getCreateRecipeViewModel().createRecipe();
  }

  public void onCancel(ActionEvent actionEvent)
  {
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
        super.getViewModels().getCreateRecipeViewModel()
            .remove(selectedItem.getIngredient().get());
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
