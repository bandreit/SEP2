package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewmodel.LogInViewModel;
import viewmodel.ViewModelFactory;

import java.awt.*;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class LogInViewController extends ViewController
{
  @FXML TextField username;
  @FXML PasswordField password;
  @FXML Label errorLabel;
  @FXML Button logIn;
  @FXML Button register;
  private LogInViewModel logInViewModel;

  public LogInViewController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
      throws RemoteException, SQLException
  {
    super.init(viewHandler, viewModels, root);
    logInViewModel = super.getViewModels().getLogInViewModel();
    username.textProperty().bindBidirectional(
        logInViewModel.getUsernameProperty());
    password.textProperty().bindBidirectional(
        logInViewModel.getPasswordProperty());
    errorLabel.textProperty()
        .bind(logInViewModel.getErrorProperty());
  }

  public void openRegisterPage(ActionEvent event)
      throws RemoteException, SQLException
  {
    super.getHandler().openView("RegisterView");
  }

  public void logInWithCredentials(ActionEvent event)
  {
  }

  public void loginButtonPressed() throws RemoteException, SQLException
  {
    if (logInViewModel.loginButtonPressed())
    {
      logInViewModel.clear();
      super.getHandler().openView("AllRecipes");
    }
  }
}
