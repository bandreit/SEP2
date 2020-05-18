package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.ListOfIngredients;
import model.Recipe;
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
  public void onAddIngredient(ActionEvent actionEvent)
  {

  }

  public void onCreate(ActionEvent actionEvent)
  {
    super.getViewModels().getCreateRecipeViewModel().createRecipe();
    super.getHandler().openView("AllRecipes");
    ListOfIngredients ingredient=new ListOfIngredients();
    Recipe recipe=new Recipe(super.getViewModels().getCreateRecipeViewModel().getRecipeName().get(),ingredient,
        super.getViewModels().getCreateRecipeViewModel().getDescription().get());
    super.getViewModels().getAllRecipesViewModel().addToTheList(recipe);
  }

  public void onCancel(ActionEvent actionEvent)
  {
  }
}
