package viewmodel;

import model.LocalModel;

public class ViewModelFactory
{
  private LogInViewModel logInViewModel;
  private RegisterViewModel registerViewModel;
  private CreateRecipeViewModel createRecipeViewModel;
  private AllRecipesViewModel allRecipesViewModel;

  public ViewModelFactory(LocalModel model)
  {
    this.logInViewModel = new LogInViewModel(model);
    this.registerViewModel = new RegisterViewModel(model);
    this.createRecipeViewModel = new CreateRecipeViewModel(model);
    this.allRecipesViewModel=new AllRecipesViewModel(model);
  }


  public CreateRecipeViewModel getCreateRecipeViewModel()
  {
    return createRecipeViewModel;
  }
  public LogInViewModel getLogInViewModel()
  {
    return logInViewModel;
  }

  public RegisterViewModel getRegisterViewModel()
  {
    return registerViewModel;
  }

  public AllRecipesViewModel getAllRecipesViewModel()
  {
    return allRecipesViewModel;
  }
}
