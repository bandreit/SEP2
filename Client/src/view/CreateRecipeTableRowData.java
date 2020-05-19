package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Ingredient;

public class CreateRecipeTableRowData
{
  private StringProperty ingredient;
  private IntegerProperty quantity;
  private StringProperty measurment;

  public CreateRecipeTableRowData(Ingredient theIngredient)
  {
    String IngredientName = null;
    int AmountOfQuantity = 0;
    String Measurement = null;

    if (theIngredient != null)
    {
      IngredientName = theIngredient.getIngredient();
      AmountOfQuantity = theIngredient.getAmount();
      Measurement = theIngredient.getMeasurement();
    }

    ingredient = new SimpleStringProperty(IngredientName);
    quantity = new SimpleIntegerProperty(AmountOfQuantity);
    measurment = new SimpleStringProperty(Measurement);

  }

  public StringProperty getIngredient()
  {
    return ingredient;
  }

  public StringProperty getMeasurement()
  {
    return measurment;
  }

  public IntegerProperty getQuantity()
  {
    return quantity;
  }
}

