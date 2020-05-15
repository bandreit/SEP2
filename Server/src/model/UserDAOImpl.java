package model;

import java.sql.*;

public class UserDAOImpl implements UserDAO
{

  private static UserDAOImpl instance;

  private UserDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized UserDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new UserDAOImpl();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=recipenetwork",
        "postgres", "1234");
  }

  @Override public User create(String username, String password, String email)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO USERS(username, email, password) VALUES (?, ?, crypt(?, gen_salt('bf', 8)));",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, username);
      statement.setString(2, email);
      statement.setString(3, password);

      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if (keys.next())
      {
        return new User(keys.getInt(1), username, email, password);
      }
      else
      {
        throw new SQLException("No keys generated");
      }
    }
  }

  @Override public User readById(int id) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM user WHERE id = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {

      }
      else
      {
        return null;
      }

    }
    return null;
  }

  @Override public void update(User user) throws SQLException
  {

  }

  @Override public void delete(User user) throws SQLException
  {

  }
}
