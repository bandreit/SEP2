package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        "postgres", "1");
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
            preparationTime, category,userId);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public Recipe editRecipe(int id, String recipeName,
      String description, ListOfIngredients ingredients, String instructions,
      int preparationTime, String category, int userId) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE recipes SET name=?, description=?, instructions=?, preperation_time=?, category=? WHERE id=?",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, recipeName);
      statement.setString(2, description);
      statement.setString(3, instructions);
      statement.setInt(4, preparationTime);
      statement.setString(5, category);
      statement.setInt(6, id);

      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new Recipe(keys.getInt(1), recipeName, description, instructions,
            preparationTime, category,id);
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

  @Override public RecipeList getRecipes() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM RECIPES");
      ResultSet resultSet = statement.executeQuery();
      RecipeList result = new RecipeList();
      while (resultSet.next())
      {
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String category = resultSet.getString("category");
        int id = resultSet.getInt("id");
        String instructions = resultSet.getString("instructions");
        int preparation_time = resultSet.getInt("preperation_time");
        int ownerId = resultSet.getInt("owner_id");
        Recipe recipe = new Recipe(id, name, description, instructions,
            preparation_time, category,ownerId);
        result.addRecipe(recipe);
      }
      return result;
     }
  }

  @Override public RecipeList getRecipesForUser(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM RECIPES where owner_id = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      RecipeList result = new RecipeList();
      while (resultSet.next())
      {
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String category = resultSet.getString("category");
        int recipeId = resultSet.getInt("id");
        String instructions = resultSet.getString("instructions");
        int preparation_time = resultSet.getInt("preperation_time");
        int ownerId = resultSet.getInt("owner_id");
        Recipe recipe = new Recipe(recipeId, name, description, instructions,
            preparation_time, category,ownerId);
        result.addRecipe(recipe);
      }
      return result;
    }
  }

  @Override public RecipeList searchRecipes(String searchString)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM RECIPES where LOWER(name) LIKE ?");
      statement.setString(1, "%" + searchString + "%");
      ResultSet resultSet = statement.executeQuery();
      RecipeList result = new RecipeList();
      while (resultSet.next())
      {
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String category = resultSet.getString("category");
        int recipeId = resultSet.getInt("id");
        String instructions = resultSet.getString("instructions");
        int preparation_time = resultSet.getInt("preperation_time");
        int ownerId = resultSet.getInt("owner_id");
        Recipe recipe = new Recipe(recipeId, name, description, instructions,
            preparation_time, category,ownerId);

        result.addRecipe(recipe);
      }
      return result;
    }
  }

  @Override public RecipeList searchRecipesByIngredients(String searchString) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      searchString = searchString.replaceAll("\\s+", "");
      String[] ingredientsArray = searchString.split(",");
      String sql = "Select distinct r.name, r.description, r.category, r.id, r.instructions, r.preperation_time, r.owner_id from recipes r inner join recipe_ingredien_connection c on r.id = c.recipeid \n"
          + "\tinner join ingredients i on c.ingredientid = i.id where i.name LIKE ?";
      for (int i = 0; i < ingredientsArray.length - 1; i++)
      {
        sql += " or i.name LIKE ? ";
      }
      PreparedStatement statement = connection
          .prepareStatement(sql);
      statement.setString(1, "%" + searchString + "%");
      for (int i = 0; i < ingredientsArray.length - 1; i++)
      {
        statement.setString(i+2, "%" + ingredientsArray[i] + "%");
      }
      ResultSet resultSet = statement.executeQuery();
      RecipeList result = new RecipeList();
      while (resultSet.next())
      {
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String category = resultSet.getString("category");
        int recipeId = resultSet.getInt("id");
        String instructions = resultSet.getString("instructions");
        int preparation_time = resultSet.getInt("preperation_time");
        int owner_id = resultSet.getInt("owner_id");
        Recipe recipe = new Recipe(recipeId, name, description, instructions,
            preparation_time, category, owner_id);
        result.addRecipe(recipe);
      }
      return result;
    }
  }

  @Override public void deleteRecipe(int id)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM recipes WHERE id = ?");
      statement.setInt(1, id);
      statement.executeUpdate();
    }
  }

}
