package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

import java.util.HashMap;
import java.util.Map;

public class ViewControllerFactory
{
  private static Map<String, ViewController> viewControllerMap = new HashMap<>();

  public static ViewController getViewController(String id, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    ViewController viewController = viewControllerMap.get(id);

    if (viewController == null)
    {
      viewController = createViewController(id);
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewControllerFactory.class.getResource(id+".fxml"));
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

  private static ViewController createViewController(String id){
    switch (id)
    {
      case "LogInView": return new LogInViewController();
      case "RegisterView": return new RegisterViewController();
      case "CreateRecipeView": return new CreateRecipeViewController();
      case "IngredientsView": return new IngredientsViewController();
      case "AmountView": return new AmountViewController();
      default: throw new IllegalArgumentException("No such id for viw controller");
    }
  }
}
