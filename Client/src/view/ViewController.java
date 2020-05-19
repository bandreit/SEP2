package view;

import javafx.scene.layout.Region;
import viewmodel.ViewModelFactory;

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
      Region root)
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
