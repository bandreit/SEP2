package model;

import java.sql.*;

public class DiscountDAOImpl implements DiscountDAO
{

  private static DiscountDAOImpl instance;

  private DiscountDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=recipenetwork",
        "postgres", "1234");
  }

  public static synchronized DiscountDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new DiscountDAOImpl();
    }
    return instance;
  }

  @Override public ListOfDiscountItems getDiscountItems() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM DISCOUNTS");
      ResultSet resultSet = statement.executeQuery();
      ListOfDiscountItems result = new ListOfDiscountItems();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String imageUrl = resultSet.getString("details");
        String category = resultSet.getString("category");
        String discountPrice = resultSet.getString("discountprice");
        String normalPrice= resultSet.getString("normalprice");
        String link = resultSet.getString("link");
        DiscountItem discountItem = new DiscountItem(id, title, imageUrl, category, discountPrice, normalPrice, link);
        result.addDiscountItem(discountItem);
      }
      return result;
    }
  }
}
