package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import viewmodel.CreateRecipeViewModel;
import viewmodel.ViewModelFactory;

public class CreateRecipeViewController extends ViewController
{
  @FXML private TextField measurementField;
  @FXML private TextField quantityField;
  @FXML private TextField IngredientField;
  @FXML private TextField recipeName;
  @FXML private TextArea description;
  @FXML private TextArea instructions;
  @FXML private TextField time;
  //  @FXML private ChoiceBox category;
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
    //    category.textProperty().bindBidirectional(
    //        super.getViewModels().getCreateRecipeViewModel().getCategory());
    measurementField.textProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getIngredients());
    quantityField.textProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getQuantity());
    measurementField.textProperty().bindBidirectional(
        super.getViewModels().getCreateRecipeViewModel().getMeasurement());

    ingredientColumn.setCellValueFactory(cellData -> cellData.getValue().getIngredient());
    quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantity());
    measurementColumn.setCellValueFactory(cellData -> cellData.getValue().getMeasurement());

    ingredientsList.setItems(super.getViewModels().getCreateRecipeViewModel().getAllIngredients());
  }

  public void onAddIngredient(ActionEvent actionEvent)
  {
    super.getViewModels().getCreateRecipeViewModel().createIngredient();
  }

  public void onCreate(ActionEvent actionEvent)
  {
    super.getViewModels().getCreateRecipeViewModel().createRecipe();
  }

  public void onCancel(ActionEvent actionEvent)
  {
  }
}
