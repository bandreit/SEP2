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
        confirmPassword.get()))
    {
      try
      {
        model.register(username.get(), password.get(), email.get(),
            confirmPassword.get());
        return true;
      }
      catch (Exception e)
      {
        e.printStackTrace();
        errorLabel.setValue(e.getMessage());
      }
    }
    return false;
  }

  private boolean validateRegister(String user, String password, String email,
      String confirmPassword)
  {
    if (user == null || user.isEmpty())
    {
      errorLabel.setValue("Username cannot be empty");
      return false;
    }
    else if (password == null || password.length() < 6
        || confirmPassword == null)
    {
      errorLabel.setValue("Password must contain at least 6 characters");
      return false;
    }
    else if (!confirmPassword.equals(password))
    {
      errorLabel.setValue("Passwords does not match");
      return false;
    }
    else if (email == null)
    {
      errorLabel.setValue("No email provided");
      return false;
    }
    else if (!email.contains("@"))
    {
      errorLabel.setValue("Email does not contain @");
      return false;
    }
    return true;
  }

  public void clear()
  {
    username.set("");
    password.set("");
    confirmPassword.set("");
    email.set("");
    errorLabel.set("");
  }
}
