import javafx.application.Application;
import javafx.stage.Stage;
import model.LocalModel;
import model.LocalModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class MyApplication extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    try
    {
      LocalModel model = new LocalModelManager();
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);
      view.start(primaryStage);
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }
}
