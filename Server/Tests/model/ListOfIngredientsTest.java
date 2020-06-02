package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListOfIngredientsTest
{
  private ListOfIngredients list;
  private ListOfIngredients populatedList;
  private Ingredient ingredient;
  private Ingredient ingredient2;
  private Ingredient ingredient3;

  @BeforeEach void setUp()
  {
    list = new ListOfIngredients();
    populatedList = new ListOfIngredients();
    ingredient = new Ingredient(1, "Garlic", 2, "pcs.");
    ingredient2 = new Ingredient(5, "Water", 1, "l");
    ingredient3 = new Ingredient(7, "Rice", 70, "g");
    populatedList.addIngredient(ingredient);
    populatedList.addIngredient(ingredient2);
    populatedList.addIngredient(ingredient3);
  }

  @Test void addIngredientZero()
  {
    // no need to be tested
  }

  @Test void addIngredientOne()
  {
    list.addIngredient(ingredient);
    assertEquals(list.getIngredient(0), ingredient);
  }

  @Test void addIngredientMany()
  {
    list.addIngredient(ingredient);
    list.addIngredient(ingredient2);
    list.addIngredient(ingredient3);
    assertEquals(list.getIngredient(0), ingredient);
    assertEquals(list.getIngredient(1), ingredient2);
    assertEquals(list.getIngredient(2), ingredient3);
  }

  @Test void addIngredientBoundary()
  {
    // no need to test
  }

  @Test void addIngredientExceptions()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      list.addIngredient(null);
    });
  }

  @Test void getIngredientZero()
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getIngredient(0);
    });
  }

  @Test void getIngredientOne()
  {
    list.addIngredient(ingredient);
    assertEquals(list.getIngredient(0), ingredient);
  }

  @Test void getIngredientMany()
  {
    list.addIngredient(ingredient);
    list.addIngredient(ingredient2);
    list.addIngredient(ingredient3);
    assertEquals(list.getIngredient(0), ingredient);
    assertEquals(list.getIngredient(1), ingredient2);
    assertEquals(list.getIngredient(2), ingredient3);
  }

  @Test void getIngredientBoundary()
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getIngredient(-1);
    });

    list.addIngredient(ingredient);
    list.addIngredient(ingredient2);
    list.addIngredient(ingredient3);
    assertEquals(list.getIngredient(0),ingredient);
    assertEquals(list.getIngredient(1), ingredient2);
    assertEquals(list.getIngredient(2), ingredient3);

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getIngredient((Integer.MAX_VALUE));
    });
  }

  @Test void getIngredientExceptions()
  {
    list.addIngredient(ingredient);
    list.addIngredient(ingredient2);
    list.addIngredient(ingredient3);

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getIngredient(-100);
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getIngredient(175);
    });
  }

  @Test void getSizeZero()
  {
    assertEquals(0, list.getSize());
  }

  @Test void getSizeOne()
  {
    list.addIngredient(ingredient);
    assertEquals(1, list.getSize());
  }

  @Test void getSizeMany()
  {
    assertEquals(3, populatedList.getSize());
  }

  @Test void getSizeBoundary()
  {
    // can't be tested
  }

  @Test void getSizeExceptions()
  {
    // can't be tested
  }

  @Test void removeIngredientZero()
  {
    list.removeIngredient(null);
  }

  @Test void removeIngredientOne()
  {
    list.removeIngredient("Garlic");
    assertEquals(0, list.getSize());
  }

  @Test void removeIngredientMany()
  {
    populatedList.removeIngredient("Water");
    assertEquals(2, populatedList.getSize());
    populatedList.removeIngredient("Garlic");
    assertEquals(1, populatedList.getSize());
    populatedList.removeIngredient("Rice");
    assertEquals(0, populatedList.getSize());
  }

  @Test void getIngredientByIdBoundary()
  {
    populatedList.removeIngredient("Water");
    assertEquals(2, populatedList.getSize());
    populatedList.removeIngredient("Garlic");
    assertEquals(1, populatedList.getSize());
    populatedList.removeIngredient("Rice");
    assertEquals(0, populatedList.getSize());
    populatedList.removeIngredient("Rice");
    assertEquals(0, populatedList.getSize());
  }

  @Test void getIngredientByIdExceptions()
  {
    // not possible
  }

}