package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ingredient;
import model.ListOfIngredients;
import model.LocalModel;
import model.Recipe;
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
  private IntegerProperty quantity;
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
    this.quantity = new SimpleIntegerProperty();
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

  public IntegerProperty getQuantity()
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
    if (validateIngredientFields())
    {
      try
      {
        Ingredient ingredient = new Ingredient(listOfIngredients.size(),
            ingredientName.get(), quantity.get(), measurement.get());
        clearIngredientField();
        model.addFullIngredientWithQtyAndAMeasurement(ingredient);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }

  private boolean validateIngredientFields()
  {
    if ((ingredientName.get() == null) || ingredientName.get().isEmpty())
    {
      deleteErrorLabel.setValue("Name for ingredient not provided");
      return false;
    }
    if (quantity.get() == 0)
    {
      deleteErrorLabel.setValue("The quantity cannot be 0");
      return false;
    }
    if (measurement.get() == null)
    {
      deleteErrorLabel.setValue("Measurement type not indicated");
      return false;
    }
    return true;
  }

  public boolean validateRecipeFields()
  {
    if ((recipeName.get() == null) || recipeName.get().isEmpty())
    {
      deleteErrorLabel.setValue("Name for recipe not provided");
      return false;
    }
    if (description.get() == null)
    {
      deleteErrorLabel.setValue("Description not provided");
      return false;
    }
    if (time.get() == null)
    {
      deleteErrorLabel.setValue("Please indicate preparation time");
      return false;
    }
    if (category.get() == null)
    {
      deleteErrorLabel.setValue("Please indicate a category");
      return false;
    }
    if (instructions.get() == null)
    {
      deleteErrorLabel.setValue("Please indicate instructions");
      return false;
    }
    if (listOfIngredients.isEmpty())
    {
      deleteErrorLabel.setValue("No ingredients provided");
      return false;
    }
    return true;
  }

  public void clearIngredientField()
  {
    ingredientName.set(null);
    quantity.set(0);
    measurement.set(null);
  }

  public void clear()
  {
    deleteErrorLabel.setValue(null);
    recipeName.setValue(null);
    description.setValue(null);
    category.setValue(null);
    instructions.setValue(null);
    time.setValue(null);
    listOfIngredients.removeAll();
  }

  @Override public void propertyChange(
      ObserverEvent<Ingredient, Ingredient> event)
  {
    Platform.runLater(() -> {
      listOfIngredients.add(new CreateRecipeTableRowData(event.getValue2()));
    });
  }

  public Recipe recipe()
  {
    return new Recipe(recipeName.get(), description.get(),
        model.getListOfIngredients(), instructions.get(),
        Integer.parseInt(time.get()), category.get());
  }

  public void createRecipe() throws RemoteException
  {
    model.createRecipe(recipeName.get(), description.get(),
        model.getListOfIngredients(), instructions.get(),
        Integer.parseInt(time.get()), category.get());
    clear();
  }

  /// GET THE CATEGORY FROM THE VIEW

  private void updateIngredients()
  {
    listOfIngredients = FXCollections.observableArrayList();
    ListOfIngredients list = model.getListOfIngredients();
    for (int i = 0; i < list.getSize(); i++)
    {
      listOfIngredients
          .add(new CreateRecipeTableRowData(list.getIngredient(i)));
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


