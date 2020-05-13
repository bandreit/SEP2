package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LocalModel;

public class LogInViewModel
{
  private LocalModel model;
  private StringProperty username;
  private StringProperty password;
  private StringProperty error;

  public LogInViewModel(LocalModel model)
  {
    this.model = model;
    this.username = new SimpleStringProperty();
    this.password = new SimpleStringProperty();
    this.error = new SimpleStringProperty();
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
    return error;
  }

  public void loginButtonPressed()
  {
    try
    {
//      System.out.println(username.get() + "-------------" + password.get());//get is correct to get values <<<
      model.login(username.get(), password.get());
    }
    catch (Exception e)
    {
      error.setValue(e.getMessage());
    }
  }
}
