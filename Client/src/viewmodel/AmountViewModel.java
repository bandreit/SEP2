package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LocalModel;

public class AmountViewModel
{
  private StringProperty amount;
  private LocalModel model;

  public AmountViewModel(LocalModel model)
  {
    this.model=model;
    amount=new SimpleStringProperty();
  }

  public StringProperty getAmount()
  {
    return amount;
  }

  public void addAmount()
  {
    model.addAmount(amount.get());
  }

  public void addIngredient(String s)
  {
    model.addIngredient(s);
  }
}
