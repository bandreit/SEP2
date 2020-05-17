package model;

import java.util.ArrayList;

public class ListOfIngredients
{
  private ArrayList<Ingredient> ingredients;

  public ListOfIngredients()
  {
    ingredients=new ArrayList<>();
  }
  public void addIngredient(Ingredient ingredient)
  {
    ingredients.add(ingredient);
  }
  public void removeIngredient(Ingredient ingredient)
  {
    ingredients.remove(ingredient);
  }

  public Ingredient getIngredient(int index)
  {
    return ingredients.get(index);
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
