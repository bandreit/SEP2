package model;

import java.sql.*;

public class CommentDAOImpl implements CommentDAO
{

  private static CommentDAOImpl instance;

  private CommentDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=recipenetwork",
        "postgres", "1");
  }

  public static synchronized CommentDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new CommentDAOImpl();
    }
    return instance;
  }

  @Override public String getComments(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "select text,username from Comments where recipe_id = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      String comments = "";
      while (resultSet.next())
      {
        String username = resultSet.getString("username");
        String text = resultSet.getString("text");
        comments += username + ": " + text + "\n";
      }
      return comments;
    }
  }

  @Override public String create(int id, int user, String text)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO Comments(recipe_id, userName, text) VALUES (?, (SELECT username from users where id = ?), ?);",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, id);
      statement.setInt(2, user);
      statement.setString(3, text);

      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
    }
    return null;
  }
}