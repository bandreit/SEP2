package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Recipe;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.TableRowData;

import java.util.ArrayList;

public class ServerViewModel implements LocalListener<Recipe, Recipe>
{
  private Model model;
  private StringProperty nameField;
  private StringProperty numberField;
  private ObservableList<TableRowData> list;

  public ServerViewModel(Model model) throws Exception
  {
    this.model = model;
    this.model.addListener(this, "add");
    this.nameField = new SimpleStringProperty();
    this.numberField = new SimpleStringProperty();
  }

  public StringProperty getNameFieldProperty()
  {
    return nameField;
  }

  public StringProperty getNumberFieldProperty()
  {
    return numberField;
  }

  public ObservableList<TableRowData> getList()
  {
    return list;
  }


  public void setMessage(String inputField)
  {
    //    model.addMessage(inputField);
  }


  private void addToTheList(Recipe recipe)
  {
    list.add(new TableRowData(recipe));
  }

  @Override public void propertyChange(ObserverEvent<Recipe, Recipe> event)
  {
    Platform.runLater(() -> {
      addToTheList(event.getValue2());
    });
  }
}
