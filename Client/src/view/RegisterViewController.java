package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
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

  public RegisterViewController()
  {
    super();
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModels, Region root)
      throws RemoteException, SQLException
  {
    super.init(viewHandler, viewModels, root);
    username.textProperty().bindBidirectional(
        super.getViewModels().getRegisterViewModel().getUsernameProperty());
    password.textProperty().bindBidirectional(
        super.getViewModels().getRegisterViewModel().getPasswordProperty());
    errorLabel.textProperty()
        .bind(super.getViewModels().getRegisterViewModel().getErrorProperty());
    confirmPassword.textProperty().bindBidirectional(
        super.getViewModels().getRegisterViewModel()
            .getConfirmPasswordProperty());
    email.textProperty().bindBidirectional(
        super.getViewModels().getRegisterViewModel().getEmailProperty());
  }

  public void backToLogIn(MouseEvent mouseEvent)
  {
    super.getHandler().openView("LogInView");
  }

  public void RegisterButtonPressed()
  {
    if (super.getViewModels().getRegisterViewModel().registerAccount())
    {
      super.getHandler().openView("LogInView");//move on if correct
      super.getViewModels().getRegisterViewModel().clear();
    }
  }

}
