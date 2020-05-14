package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LocalModel;

public class LogInViewModel
{
  private LocalModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty errorLabel;

  public LogInViewModel(LocalModel model)
  {
    this.model = model;
    this.username = new SimpleStringProperty();
    this.password = new SimpleStringProperty();
    this.errorLabel = new SimpleStringProperty();
  }

  public StringProperty getUsernameProperty()
  {
    return username;
  }

  public StringProperty getPasswordProperty()
  {
    return password;
  }

  public StringProperty getErrorProperty()
  {
    return errorLabel;
  }

  public boolean loginButtonPressed()
  {
    if (validateLogin(username.get(), password.get()) == true)
    {
      try
      {
        model.login(username.get(), password.get());
      }
      catch (Exception e)
      {
        errorLabel.setValue(e.getMessage());
      }
      return true;
    }
    else
    {
      return false;
    }
  }

  private boolean validateLogin(String user, String password)
  {
    try
    {
      if (user == null || user.isEmpty())
      {
        errorLabel.setValue("Username cannot be empty");
        throw new IllegalArgumentException("Username cannot be empty");
      }
      if (password == null || password.length() < 6)
      {
        errorLabel.setValue("Password must contain at least 6 characters");
        throw new IllegalArgumentException("Password must contain at least 6 characters");
      }
//      if(not in database)
//      {
//        throw error
//      }
      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }
}
