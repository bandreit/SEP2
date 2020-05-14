package viewmodel;

import model.LocalModel;

public class ViewModelFactory
{
  private LogInViewModel logInViewModel;
  private RegisterViewModel registerViewModel;
  private CreateRecipeViewModel createRecipeViewModel;
  private IngredientsViewModel ingredientsViewModel;
  private AmountViewModel amountViewModel;

  public ViewModelFactory(LocalModel model)
  {
    this.logInViewModel = new LogInViewModel(model);
    this.registerViewModel = new RegisterViewModel(model);
    this.createRecipeViewModel = new CreateRecipeViewModel(model);
    this.ingredientsViewModel=new IngredientsViewModel(model);
    this.amountViewModel=new AmountViewModel(model);
  }

  public AmountViewModel getAmountViewModel()
  {
    return amountViewModel;
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

  public IngredientsViewModel getIngredientsViewModel()
  {
    return ingredientsViewModel;
  }
}
