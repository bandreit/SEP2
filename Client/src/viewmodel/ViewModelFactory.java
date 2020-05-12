package viewmodel;

import model.LocalModel;

public class ViewModelFactory
{
  private LogInViewModel logInViewModel;
  private RegisterViewModel registerViewModel;

  public ViewModelFactory(LocalModel model)
  {
    this.logInViewModel = new LogInViewModel(model);
    this.registerViewModel = new RegisterViewModel(model);
  }

  public LogInViewModel getLogInViewModel()
  {
    return logInViewModel;
  }

  public RegisterViewModel getRegisterViewModel()
  {
    return registerViewModel;
  }
}
