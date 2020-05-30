package model;

import java.sql.SQLException;

public interface CommentDAO
{
  String getComments(int id) throws SQLException;
  String create(int Id, int user, String text) throws SQLException;
}
