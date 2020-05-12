package view;

import javafx.scene.input.MouseEvent;

public class RegisterViewController extends ViewController
{
  public RegisterViewController()
  {
    super();
  }

  public void backToLogIn(MouseEvent mouseEvent)
  {
    super.getHandler().openView("LogInView");
  }
}
