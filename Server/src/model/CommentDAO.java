package model;

import java.sql.SQLException;

public interface CommentDAO
{
  String getComments(int id) throws SQLException;
  String create(int Id, String userName,String text) throws SQLException;
}
