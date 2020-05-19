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
    if (validateLogin(username.get(), password.get()))
    {
      try
      {
        if (model.login(username.get(), password.get()))
        {
          return true;
        };
          errorLabel.set("Wrong password!");
      }
      catch (Exception e)
      {
//        e.printStackTrace();
        errorLabel.setValue(e.getMessage());
      }
    }
    return false;
  }

  private boolean validateLogin(String user, String password)
  {
    if (user == null || user.isEmpty())
    {
      errorLabel.setValue("Username cannot be empty");
      return false;
    }
    if (password == null || password.length() < 6)
    {
      errorLabel.setValue("Password must contain at least 6 characters");
      return false;
    }
    return true;
  }

  public void clear()
  {
    username.set("");
    password.set("");
    errorLabel.set("");
  }
}
