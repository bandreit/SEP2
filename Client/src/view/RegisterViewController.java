package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import viewmodel.RegisterViewModel;
import viewmodel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class RegisterViewController extends ViewController
{
  @FXML TextField username;
  @FXML PasswordField password;
  @FXML Label errorLabel;
  @FXML PasswordField confirmPassword;
  @FXML TextField email;
  private   RegisterViewModel registerViewModel;

  public RegisterViewController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
      throws RemoteException, SQLException
  {
    super.init(viewHandler, viewModels, root);
    registerViewModel = super.getViewModels()
        .getRegisterViewModel();
    username.textProperty().bindBidirectional(
        registerViewModel.getUsernameProperty());
    password.textProperty().bindBidirectional(
        registerViewModel.getPasswordProperty());
    errorLabel.textProperty()
        .bind(registerViewModel.getErrorProperty());
    confirmPassword.textProperty().bindBidirectional(
        registerViewModel
            .getConfirmPasswordProperty());
    email.textProperty().bindBidirectional(
        registerViewModel.getEmailProperty());
  }

  public void backToLogIn(MouseEvent mouseEvent)
      throws RemoteException, SQLException
  {
    super.getHandler().openView("LogInView");
  }

  public void RegisterButtonPressed() throws RemoteException, SQLException
  {
    if (registerViewModel.registerAccount())
    {
      super.getHandler().openView("LogInView");//move on if correct
      registerViewModel.clear();
    }
  }

}
