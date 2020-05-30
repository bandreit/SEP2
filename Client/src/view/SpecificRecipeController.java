package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
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

  public SpecificRecipeController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
      throws RemoteException, SQLException
  {
    super.init(viewHandler, viewModels, root);
    recipeName.textProperty().bindBidirectional(
        super.getViewModels().getSpecificRecipeViewModel()
            .getRecipeNameProperty());
    category.textProperty().bindBidirectional(
        super.getViewModels().getSpecificRecipeViewModel()
            .getCategoryProperty());
    time.textProperty().bindBidirectional(
        super.getViewModels().getSpecificRecipeViewModel().getTimeProperty());
    description.textProperty().bindBidirectional(
        super.getViewModels().getSpecificRecipeViewModel()
            .getDescriptionProperty());
    ingredients.textProperty().bindBidirectional(
        super.getViewModels().getSpecificRecipeViewModel()
            .getIngredientsProperty());
    directions.textProperty().bindBidirectional(
        super.getViewModels().getSpecificRecipeViewModel()
            .getDirectionsProperty());
    writeComment.textProperty().bindBidirectional(
        super.getViewModels().getSpecificRecipeViewModel().getWriteComment());
    comments.textProperty().bindBidirectional(
        super.getViewModels().getSpecificRecipeViewModel().getComments());
  }

  public void goBack(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    super.getHandler().openView("AllRecipes");
  }

  public void AddComment(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    super.getViewModels().getSpecificRecipeViewModel().setComment();
  }

  public void openDiscounts(MouseEvent mouseEvent)
      throws RemoteException, SQLException
  {
    super.getHandler().openView("Discounts");
  }
}
