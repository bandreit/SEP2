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

public class CreateRecipeViewModel
    implements LocalListener<Ingredient, Ingredient>
{
  private StringProperty ingredients;
  private LocalModel model;
  private StringProperty quantity;
  private StringProperty measurement;
  private StringProperty description;
  private StringProperty recipeName;
  private StringProperty instructions;
  private StringProperty time;
  private StringProperty category;
  private ObservableList<CreateRecipeTableRowData> listOfIngredients;

  public CreateRecipeViewModel(LocalModel model)
  {
    this.model = model;
    this.ingredients = new SimpleStringProperty();
    this.quantity = new SimpleStringProperty();
    this.measurement = new SimpleStringProperty();
    this.time = new SimpleStringProperty();
    this.description = new SimpleStringProperty();
    this.category = new SimpleStringProperty();
    this.recipeName = new SimpleStringProperty();
    this.instructions = new SimpleStringProperty();
    updateIngredients();
    this.model.addListener(this, "addIngredient");
  }

  public StringProperty getIngredients()
  {
    return ingredients;
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

  public StringProperty getCategory()
  {
    return category;
  }

  public StringProperty getRecipeName()
  {
    return recipeName;
  }

  public StringProperty getInstructions()
  {
    return instructions;
  }

  public ObservableList<CreateRecipeTableRowData> getAllIngredients()
  {
    return listOfIngredients;
  }

  public void createIngredient()
  {
    try
    {
      Ingredient ingredient = new Ingredient(ingredients.get(), quantity.get(),
          measurement.get());
      clear();
      model.addFullIngredientWithQtyAndAMeasurement(ingredient);
      //      result.set("Added: " + student);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      //      result.set(e.getMessage());
    }
  }

  public void clear()
  {
    ingredients.set(null);
    quantity.set(null);
    measurement.set(null);
  }

  @Override public void propertyChange(
      ObserverEvent<Ingredient, Ingredient> event)
  {
    Platform.runLater(() -> {
      listOfIngredients.add(new CreateRecipeTableRowData(event.getValue2()));
//     System.out.println(listOfIngredients);
//      System.out.println(listOfIngredients.size());
    });
  }

  public void createRecipe()
  {
    model.createRecipe(recipeName.get(), model.getListOfIngredients(),
        description.get());
  }

  private void updateIngredients()
  {
    listOfIngredients = FXCollections.observableArrayList();
    ListOfIngredients list = model.getListOfIngredients();
    for (int i = 0; i < list.getSize(); i++)
    {
      listOfIngredients.add(new CreateRecipeTableRowData(list.getIngredient(i)));
    }
  }
}

