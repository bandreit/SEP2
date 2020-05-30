package model;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO
{
  User create(String username, String email, String password)
      throws SQLException;
  boolean doesUserExist(String username) throws SQLException;
  int logInUser(String username, String password) throws SQLException;
}
