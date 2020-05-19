package model;

import java.sql.SQLException;

public interface IngredientDAO
{
  Ingredient create(String ingredient, int amount, String measurement)
      throws SQLException;
}
