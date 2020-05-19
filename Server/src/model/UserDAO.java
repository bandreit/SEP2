package model;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO
{
  User create(String username, String email, String password)
      throws SQLException;
  User readById(int id) throws SQLException;
  boolean doesUserExist(String username) throws SQLException;
  boolean logInUser(String username, String password) throws SQLException;
  void update(User user) throws SQLException;
  void delete(User user) throws SQLException;
}
