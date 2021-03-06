package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import model.LocalModel;
import viewmodel.LogInViewModel;
import viewmodel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ViewControllerFactory
{
  private static Map<String, ViewController> viewControllerMap = new HashMap<>();

  public static ViewController getViewController(String id,
      ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    ViewController viewController = viewControllerMap.get(id);

    if (viewController == null)
    {
      viewController = createViewController(id);
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader
            .setLocation(ViewControllerFactory.class.getResource(id + ".fxml"));
        Region root = loader.load();
        viewController = loader.getController();
        viewController.init(viewHandler, viewModelFactory, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    viewControllerMap.put(id, viewController);
    viewController.reset();
    return viewController;
  }

  private static ViewController createViewController(String id)
  {
    switch (id)
    {
      case "LogInView":
        return new LogInViewController();
      case "RegisterView":
        return new RegisterViewController();
      case "CreateRecipeView":
        return new CreateRecipeViewController();
      case "AllRecipes":
        return new AllRecipesViewController();
      case "MyRecipes":
        return new MyRecipesViewController();
      case "SpecificRecipe":
        return new SpecificRecipeController();
      case "Discounts":
        return new DiscountsController();
      default:
        throw new IllegalArgumentException("No such id for view controller");
    }
  }
}
