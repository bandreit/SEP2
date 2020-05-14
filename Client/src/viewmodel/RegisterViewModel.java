package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LocalModel;

public class RegisterViewModel
{
  private LocalModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty confirmPassword;
  private StringProperty email;
  private StringProperty errorLabel;

  public RegisterViewModel(LocalModel model)
  {
    this.model = model;
    this.username = new SimpleStringProperty();
    this.password = new SimpleStringProperty();
    this.errorLabel = new SimpleStringProperty();
    this.confirmPassword = new SimpleStringProperty();
    this.email = new SimpleStringProperty();
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

  public StringProperty getConfirmPasswordProperty()
  {
    return confirmPassword;
  }

  public StringProperty getEmailProperty()
  {
    return email;
  }

  public boolean registerAccount()
  {
    if (validateRegister(username.get(), password.get(), email.get(),
        confirmPassword.get()) == true)
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

  private boolean validateRegister(String user, String password, String email, String confirmPassword)
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
        throw new IllegalArgumentException(
            "Password must contain at least 6 characters");
      }
      else if (!confirmPassword.equals(password))
      {
        errorLabel.setValue("Passwords does not match");
        throw new IllegalArgumentException("Passwords does not match");
      }
      else if (!email.contains("@"))
      {
        errorLabel.setValue("Email does not contain @");
        throw new IllegalArgumentException("Email does not contain @");
      }
      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }

}
