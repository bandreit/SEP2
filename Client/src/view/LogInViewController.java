package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

import java.awt.*;

public class LogInViewController extends ViewController
{
  @FXML TextField username;
  @FXML PasswordField password;
  @FXML Label errorLabel;
  @FXML Button logIn;
  @FXML Button register;


  public LogInViewController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
  {
    super.init(viewHandler, viewModels, root);
    username.textProperty().bindBidirectional(super.getViewModels().getLogInViewModel().getUsernameProperty());
    password.textProperty().bindBidirectional(super.getViewModels().getLogInViewModel().getPasswordProperty());
    errorLabel.textProperty().bind(super.getViewModels().getLogInViewModel().getErrorProperty());
  }

  public void openRegisterPage(ActionEvent event)
  {
    super.getHandler().openView("RegisterView");
  }

  public void logInWithCredentials(ActionEvent event)
  {
  }

  public void loginButtonPressed()
  {
    //if(super.getViewModels().getLogInViewModel().loginButtonPressed())
    //{
//      super.getHandler().openView(""); //add further view
      //super.getViewModels().getLogInViewModel().clear();
      super.getHandler().openView("CreateRecipeView");
  //}
  }
}
