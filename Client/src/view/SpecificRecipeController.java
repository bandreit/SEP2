package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import viewmodel.SpecificRecipeViewModel;
import viewmodel.ViewModelFactory;

import java.awt.*;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class SpecificRecipeController extends ViewController
{
  @FXML public Label category;
  @FXML public Label time;
  @FXML public Label description;
  @FXML public Label ingredients;
  @FXML public Label directions;
  @FXML public TextArea writeComment;
  @FXML public Label comments;
  @FXML public Label recipeName;
  private     SpecificRecipeViewModel specificRecipeViewModel;

  public SpecificRecipeController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
      throws RemoteException, SQLException
  {
    super.init(viewHandler, viewModels, root);
    specificRecipeViewModel = super.getViewModels()
        .getSpecificRecipeViewModel();
    recipeName.textProperty().bindBidirectional(
        specificRecipeViewModel
            .getRecipeNameProperty());
    category.textProperty().bindBidirectional(
        specificRecipeViewModel
            .getCategoryProperty());
    time.textProperty().bindBidirectional(
        specificRecipeViewModel.getTimeProperty());
    description.textProperty().bindBidirectional(
        specificRecipeViewModel
            .getDescriptionProperty());
    ingredients.textProperty().bindBidirectional(
        specificRecipeViewModel
            .getIngredientsProperty());
    directions.textProperty().bindBidirectional(
        specificRecipeViewModel
            .getDirectionsProperty());
    writeComment.textProperty().bindBidirectional(
        specificRecipeViewModel.getWriteComment());
    comments.textProperty().bindBidirectional(
        specificRecipeViewModel.getComments());
  }

  public void goBack(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    super.getHandler().openView("AllRecipes");
  }

  public void AddComment(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    specificRecipeViewModel.setComment();
  }

  public void openDiscounts(MouseEvent mouseEvent)
      throws RemoteException, SQLException
  {
    super.getHandler().openView("Discounts");
  }
}
