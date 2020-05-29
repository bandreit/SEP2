package viewmodel;

import model.LocalModel;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewModelFactory
{
  private LogInViewModel logInViewModel;
  private RegisterViewModel registerViewModel;
  private CreateRecipeViewModel createRecipeViewModel;
  private MyRecipesViewModel myRecipesViewModel;
  private AllRecipesViewModel allRecipesViewModel;
  private SpecificRecipeViewModel specificRecipeViewModel;
  private DiscountsViewModel discountsViewModel;

  public ViewModelFactory(LocalModel model) throws RemoteException, SQLException
  {
    this.logInViewModel = new LogInViewModel(model);
    this.registerViewModel = new RegisterViewModel(model);
    this.createRecipeViewModel = new CreateRecipeViewModel(model);
    this.allRecipesViewModel=new AllRecipesViewModel(model);
    this.myRecipesViewModel=new MyRecipesViewModel(model);
    this.specificRecipeViewModel = new SpecificRecipeViewModel(model);
    this.discountsViewModel = new DiscountsViewModel(model);
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

  public SpecificRecipeViewModel getSpecificRecipeViewModel()
  {
    return specificRecipeViewModel;
  }

  public DiscountsViewModel getDiscountsViewModel()
  {
    return discountsViewModel;
  }
}
