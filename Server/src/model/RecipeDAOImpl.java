package model;

import java.sql.*;

public class RecipeDAOImpl implements RecipeDAO
{
  private static RecipeDAOImpl instance;

  private RecipeDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized RecipeDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new RecipeDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=recipenetwork",
        "postgres", "1234");
  }

  @Override public Recipe createRecipe(String recipeName, String description,
      ListOfIngredients ingredients, String instructions, int preparationTime,
      String category, int userId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO recipes(name, description, instructions, preperation_time, category, owner_id) VALUES (?, ?, ?, ?, ?, ?);",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, recipeName);
      statement.setString(2, description);
      statement.setString(3, instructions);
      statement.setInt(4, preparationTime);
      statement.setString(5, category);
      statement.setInt(6, userId);

      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new Recipe(keys.getInt(1), recipeName, description, instructions,
            preparationTime, category);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public void addIngredientsToRecipe(int recipeId, int ingredientID)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO recipe_ingredien_connection(recipeId, ingredientId) VALUES (?, ?);",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, recipeId);
      statement.setInt(2, ingredientID);

      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        System.out.println("related ingredients to recipe");
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }
}
