package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ingredient;
import model.ListOfIngredients;
import model.LocalModel;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;
import view.CreateRecipeTableRowData;

import java.rmi.RemoteException;
import java.util.Arrays;

public class CreateRecipeViewModel
    implements LocalListener<Ingredient, Ingredient>
{
  private StringProperty ingredientName;
  private LocalModel model;
  private StringProperty quantity;
  private StringProperty measurement;
  private StringProperty description;
  private StringProperty recipeName;
  private StringProperty instructions;
  private StringProperty time;
  private StringProperty category;
  private StringProperty deleteErrorLabel;
  private ObservableList<CreateRecipeTableRowData> listOfIngredients;

  public CreateRecipeViewModel(LocalModel model)
  {
    this.model = model;
    this.ingredientName = new SimpleStringProperty();
    this.quantity = new SimpleStringProperty();
    this.measurement = new SimpleStringProperty();
    this.time = new SimpleStringProperty();
    this.description = new SimpleStringProperty();
    this.recipeName = new SimpleStringProperty();
    this.instructions = new SimpleStringProperty();
    this.category = new SimpleStringProperty();
    this.deleteErrorLabel = new SimpleStringProperty();
    updateIngredients();
    this.model.addListener(this, "addIngredient");
  }

  public StringProperty getDeleteErrorLabel()
  {
    return deleteErrorLabel;
  }

  public StringProperty getIngredientName()
  {
    return ingredientName;
  }

  public StringProperty getMeasurement()
  {
    return measurement;
  }

  public StringProperty getQuantity()
  {
    return quantity;
  }

  public StringProperty getTime()
  {
    return time;
  }

  public StringProperty getDescription()
  {
    return description;
  }

  public StringProperty getRecipeName()
  {
    return recipeName;
  }

  public StringProperty getInstructions()
  {
    return instructions;
  }

  public StringProperty getCategory()
  {
    return category;
  }

  public ObservableList<CreateRecipeTableRowData> getAllIngredients()
  {
    return listOfIngredients;
  }



  public void createIngredient()
  {
    try
    {
      Ingredient ingredient = new Ingredient(ingredientName.get(), quantity.get(),
          measurement.get());
      clear();
      model.addFullIngredientWithQtyAndAMeasurement(ingredient);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void clear()
  {
    ingredientName.set(null);
    quantity.set(null);
    measurement.set(null);
  }

  @Override public void propertyChange(
      ObserverEvent<Ingredient, Ingredient> event)
  {
    Platform.runLater(() -> {
      listOfIngredients.add(new CreateRecipeTableRowData(event.getValue2()));
    });
  }

  public void createRecipe() throws RemoteException
  {
    model.createRecipe(recipeName.get(), description.get(),  model.getListOfIngredients(), instructions.get(), Integer.parseInt(time.get()), category.get());
  }

  /// GET THE CATEGORY FROM THE VIEW

  private void updateIngredients()
  {
    listOfIngredients = FXCollections.observableArrayList();
    ListOfIngredients list = model.getListOfIngredients();
    for (int i = 0; i < list.getSize(); i++)
    {
      listOfIngredients.add(new CreateRecipeTableRowData(list.getIngredient(i)));
    }
  }
  public void remove(String ingredientName)
  {
    for (int i = 0; i < listOfIngredients.size(); i++)
    {
      if (listOfIngredients.get(i).getIngredient().get().equals(ingredientName))
      {
        listOfIngredients.remove(i);
        break;
      }
    }
  }
}


