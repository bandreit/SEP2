package model;

import java.sql.*;

public class IngredientDAOImpl implements IngredientDAO
{
  private static IngredientDAOImpl instance;

  private IngredientDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized IngredientDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new IngredientDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=recipenetwork",
        "postgres", "1234");
  }

  @Override public Ingredient create(String ingredient, int amount, String measurement)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO ingredients(name, amount, measurement) VALUES (?, ?, ?);",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, ingredient);
      statement.setInt(2, amount);
      statement.setString(3, measurement);

      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new Ingredient(keys.getInt(1), ingredient, amount, measurement);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public ListOfIngredients getIngredientsForRecipe(int id)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("select distinct i.id, i.name, i.amount, i.measurement from recipes r\n"
              + "\tinner join recipe_ingredien_connection c on c.recipeid = ?\n"
              + "\tinner join ingredients i on c.ingredientid = i.id;");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      ListOfIngredients result = new ListOfIngredients();
      while (resultSet.next())
      {
        int ingredientId = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int amount = resultSet.getInt("amount");
        String measurement = resultSet.getString("measurement");
        Ingredient ingredient = new Ingredient(ingredientId, name, amount, measurement);
        result.addIngredient(ingredient);
      }
      return result;
    }
  }
}