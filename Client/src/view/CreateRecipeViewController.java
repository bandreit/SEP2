package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public class CreateRecipeViewController extends ViewController
{
  @FXML private TextField recipeName;
  @FXML private TableView<ViewModelFactory> tableOfIngredients;
  @FXML private TableColumn<ViewModelFactory,String> amount;
  @FXML private TableColumn<ViewModelFactory,String> ingredients;
  @FXML private TextArea description;

  public CreateRecipeViewController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
    recipeName.textProperty().bindBidirectional(viewModels.getCreateRecipeViewModel().getRecipeName());
    description.textProperty().bindBidirectional(viewModels.getCreateRecipeViewModel().getDescription());
  }

  public void onIngredients(ActionEvent actionEvent)
  {
    super.getHandler().openView("IngredientsView");
  }

  public void onAddIngredient(ActionEvent actionEvent)
  {

  }

  public void onCreate(ActionEvent actionEvent)
  {
    super.getViewModels().getCreateRecipeViewModel().createRecipe();
  }

  public void onCancel(ActionEvent actionEvent)
  {
  }
}
