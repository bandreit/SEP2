package view;

import javafx.scene.layout.Region;
import model.Recipe;
import viewmodel.ViewModelFactory;

import java.rmi.RemoteException;
import java.sql.SQLException;

public abstract class ViewController
{
  private Region root;
  private ViewModelFactory viewModels;
  private ViewHandler viewHandler;

  public ViewController()
  {
  }

  public Region getRoot()
  {
    return this.root;
  }

  public void init(ViewHandler viewHandler, ViewModelFactory viewModels,
      Region root) throws RemoteException, SQLException
  {
    this.viewHandler = viewHandler;
    this.viewModels = viewModels;
    this.root = root;
  }

  public void reset()
  {
  }

  public ViewHandler getHandler()
  {
    return viewHandler;
  }

  public ViewModelFactory getViewModels()
  {
    return viewModels;
  }
}
