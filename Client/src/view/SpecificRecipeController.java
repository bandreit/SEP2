package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

import java.awt.*;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class SpecificRecipeController extends ViewController
{
  @FXML private Label recipeName;

  public SpecificRecipeController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root, int recipeId)
      throws RemoteException, SQLException
  {
    super.init(viewHandler, viewModels, root, recipeId);
    super.getViewModels().getSpecificRecipeViewModel().setRecipe(super.getRecipeId());
    recipeName.textProperty().bindBidirectional(
        super.getViewModels().getSpecificRecipeViewModel()
            .getRecipeNameProperty());
  }
}
