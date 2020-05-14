package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewmodel.IngredientsViewModel;
import viewmodel.ViewModelFactory;

public class IngredientsViewController extends ViewController
{
  @FXML private TableColumn<IngredientsViewModel,String> ingredients;
  @FXML private TableView<IngredientsViewModel> ingredientsList;

  public IngredientsViewController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
    ingredients.setCellValueFactory(cellData -> cellData.getValue().getIngredients());
  }

  public void onAddIngredient(ActionEvent actionEvent)
  {
    super.getHandler().openView("AmountView");
    IngredientsViewModel selectedItem=ingredientsList.getSelectionModel().getSelectedItem();
    super.getViewModels().getAmountViewModel().addIngredient(selectedItem.getIngredients().get());
  }

  public void onCancel(ActionEvent actionEvent)
  {
    super.getHandler().openView("CreateRecipeView");
  }
}
