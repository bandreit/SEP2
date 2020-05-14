package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

public class LogInViewController extends ViewController
{
  @FXML TextField username;
  @FXML TextField password;
  @FXML Button logIn;
  @FXML Button register;

  public LogInViewController()
  {
    super();
  }

  public void openRegisterPage(ActionEvent event)
  {
    super.getHandler().openView("RegisterView");
  }

  public void logInWithCredentials(ActionEvent event)
  {
    super.getHandler().openView("CreateRecipeView");
  }
}
