package viewmodel;

import model.LocalModel;

public class ViewModelFactory
{
  private LogInViewModel logInViewModel;
  private RegisterViewModel registerViewModel;
  private CreateRecipeViewModel createRecipeViewModel;
  private MyRecipesViewModel myRecipesViewModel;
  private AllRecipesViewModel allRecipesViewModel;

  public ViewModelFactory(LocalModel model)
  {
    this.logInViewModel = new LogInViewModel(model);
    this.registerViewModel = new RegisterViewModel(model);
    this.createRecipeViewModel = new CreateRecipeViewModel(model);
    this.allRecipesViewModel=new AllRecipesViewModel(model);
    this.myRecipesViewModel=new MyRecipesViewModel(model);
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

  public MyRecipesViewModel getMyRecipesViewModel()
  {
    return myRecipesViewModel;
  }

  public AllRecipesViewModel getAllRecipesViewModel()
  {
    return allRecipesViewModel;
  }
}
