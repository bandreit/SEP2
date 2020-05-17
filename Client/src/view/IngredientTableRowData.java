package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Ingredient;

public class IngredientTableRowData
{
  private StringProperty ingredient;
  private StringProperty quantity;
  public IngredientTableRowData(Ingredient Theingredient)
  {
    String IngredientName = null;
    String AmountOfQuantity = null;
    if (Theingredient != null)
    {
      IngredientName = Theingredient.getIngredient();
      AmountOfQuantity = Theingredient.getAmount();
    }
    ingredient = new SimpleStringProperty(IngredientName);
    quantity = new SimpleStringProperty(AmountOfQuantity);

  }
  public StringProperty getIngredient()
  {
    return ingredient;
  }
}

