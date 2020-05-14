package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public class AmountViewController extends ViewController
{
  @FXML private TextField amount;

  public AmountViewController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
    amount.textProperty().bindBidirectional(viewModels.getAmountViewModel().getAmount());
  }

  public void onOK(ActionEvent actionEvent)
  {
    super.getViewModels().getAmountViewModel().addAmount();
  }

  public void onCancel(ActionEvent actionEvent)
  {
    super.getHandler().openView("IngredientsView");
  }
}
