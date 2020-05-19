package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfIngredients implements Serializable
{
  private ArrayList<Ingredient> ingredients;

  public ListOfIngredients()
  {
    ingredients = new ArrayList<>();
  }

  public void addIngredient(Ingredient ingredient)
  {
    ingredients.add(ingredient);
  }

  public void removeIngredient(String ingredientName)
  {
    ingredients.remove(ingredientName);
  }

  public Ingredient getIngredient(int index)
  {
    return ingredients.get(index);
  }

  public ArrayList<Ingredient> AccessingIngredientName()
  {
    return ingredients;
  }

  @Override public String toString()
  {
    return "ListOfIngredients{" + "ingredients=" + ingredients + '}';
  }

  public int getSize()
  {
    return ingredients.size();
  }
}
