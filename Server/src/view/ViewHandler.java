package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory factory;
  private ServerViewController viewController;

  public ViewHandler(ViewModelFactory factory)
  {
    this.factory = factory;
    this.currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("serverView");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "serverView":
        root = loadServerView("Server.fxml");
        break;
    }
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();

  }

  public Region loadServerView(String xmlFile)
  {
    Region root = null;
    if (viewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(xmlFile));
        root = loader.load();
        viewController = loader.getController();
        viewController.init(this, factory.getServerViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      viewController.reset();
    }
    return viewController.getRoot();

  }

}
