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
  @FXML private TextField nameField;
  @FXML private TextField numberField;
  @FXML private TableView<TableRowData> studentListTable;
  @FXML private TableColumn<TableRowData, String> nameColumn;
  @FXML private TableColumn<TableRowData, String> numberColumn;
  @FXML private Button createNew;

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

    nameColumn
        .setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    numberColumn.setCellValueFactory(
        cellData -> cellData.getValue().getNumberProperty());
    studentListTable.setItems(viewModel.getList());

    nameField.textProperty()
        .bindBidirectional(viewModel.getNameFieldProperty());
    numberField.textProperty()
        .bindBidirectional(viewModel.getNumberFieldProperty());
  }

  public void reset()
  {
  }

  public void addStudent()
  {
    viewModel.addStudent();
  }

  //  public void onEnter()
  //  {
  //    viewModel.convert();
  //  }
  //
  //  public void onSubmit(ActionEvent event)
  //  {
  //    viewModel.convert();
  //  }

  public Region getRoot()
  {
    return root;
  }
}
