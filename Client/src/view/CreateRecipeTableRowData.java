package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Ingredient;

public class CreateRecipeTableRowData
{
  private StringProperty ingredient;
  private StringProperty quantity;
  private StringProperty measurment;

  public CreateRecipeTableRowData(Ingredient theIngredient)
  {
    String IngredientName = null;
    String AmountOfQuantity = null;
    String Measurement = null;

    if (theIngredient != null)
    {
      IngredientName = theIngredient.getIngredient();
      AmountOfQuantity = theIngredient.getAmount();
      Measurement = theIngredient.getMeasurement();
    }

    ingredient = new SimpleStringProperty(IngredientName);
    quantity = new SimpleStringProperty(AmountOfQuantity);
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

  public StringProperty getQuantity()
  {
    return quantity;
  }
}

