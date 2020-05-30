package model;

import java.sql.SQLException;

public interface DiscountDAO
{
  ListOfDiscountItems getDiscountItems() throws SQLException;
}
