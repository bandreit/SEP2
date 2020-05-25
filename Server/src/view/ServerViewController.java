package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ServerViewModel;

public class ServerViewController
{
  private Region root;
  private ServerViewModel viewModel;
  private ViewHandler handler;

  public ServerViewController()
  {

  }

  public void init(ViewHandler handler, ServerViewModel viewModel, Region root)
  {
    this.handler = handler;
    this.viewModel = viewModel;
    this.root = root;
  }

  public void reset()
  {
  }

  public Region getRoot()
  {
    return root;
  }
}
