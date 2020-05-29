package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import viewmodel.DiscountsViewModel;
import viewmodel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class DiscountsController extends ViewController
{
  public ListView<String> list;

  public DiscountsController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
      throws RemoteException, SQLException
  {
    super.init(viewHandler, viewModels, root);
    DiscountsViewModel discountsViewModel = super.getViewModels()
        .getDiscountsViewModel();
    list.setItems(discountsViewModel
        .getDiscounts(super.getViewModels().getSpecificRecipeViewModel().getCategoryProperty().get()));
    list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
    {
      public void changed(ObservableValue<? extends String> ov,
          final String oldValue, final String newValue)
      {
        System.out.println(newValue);
        discountsViewModel.openLink(newValue);
      }});
  }

  public void goBack(ActionEvent actionEvent)
      throws RemoteException, SQLException
  {
    super.getHandler().openView("SpecificRecipe");
  }
}
