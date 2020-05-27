package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.LocalModel;
import viewmodel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory factory;
  private ViewController currentVC;

  public ViewHandler(ViewModelFactory factory)
  {
    this.factory = factory;
    this.currentScene = new Scene(new Region());
    this.currentVC = null;
  }

  public void openView(String id)
      throws RemoteException, SQLException
  {
    this.currentVC = ViewControllerFactory.getViewController(id,this, factory);
    Region root = currentVC.getRoot();
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

  public void start(Stage primaryStage) throws RemoteException, SQLException
  {
    this.primaryStage = primaryStage;
    openView("LogInView");
  }
}
