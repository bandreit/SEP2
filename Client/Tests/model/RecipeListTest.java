package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeListTest
{
  private RecipeList list;
  private RecipeList populatedList;
  private Recipe recipe;
  private Recipe recipe2;
  private Recipe recipe3;

  @BeforeEach void setUp()
  {
    list = new RecipeList();
    populatedList = new RecipeList();
    recipe = new Recipe(1, "Grilled chicken", "Juicy cooked grilled chicken",
        "1. Skin the chicken\n 2. Cook it", 60, "Lunch", 1);
    recipe2 = new Recipe(5, "Grilled beef", "Juicy cooked grilled beef",
        "1. IDK\n 2. Cook it", 70, "Breakfast", 1);
    recipe3 = new Recipe(7, "Grilled egg", "Juicy cooked grilled egg",
        "1. Eggs..\n 2. Cook them", 70, "Entertainment", 1);
    populatedList.addRecipe(recipe);
    populatedList.addRecipe(recipe2);
    populatedList.addRecipe(recipe3);
  }

  @Test void addRecipeZero()
  {
    // no need to be tested
  }

  @Test void addRecipeOne()
  {
    list.addRecipe(recipe);
    assertEquals(list.getRecipe(0), recipe);
  }

  @Test void addRecipeMany()
  {
    list.addRecipe(recipe);
    list.addRecipe(recipe2);
    list.addRecipe(recipe3);
    assertEquals(list.getRecipe(0), recipe);
    assertEquals(list.getRecipe(1), recipe2);
    assertEquals(list.getRecipe(2), recipe3);
  }

  @Test void addRecipeBoundary()
  {
    // no need to test
  }

  @Test void addRecipeExceptions()
  {
    assertThrows(IllegalArgumentException.class, () -> {
      list.addRecipe(null);
    });
  }

  @Test void getRecipeZero()
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getRecipe(0);
    });
  }

  @Test void getRecipeOne()
  {
    list.addRecipe(recipe);
    assertEquals(list.getRecipe(0), recipe);
  }

  @Test void getRecipeMany()
  {
    list.addRecipe(recipe);
    list.addRecipe(recipe2);
    list.addRecipe(recipe3);
    assertEquals(list.getRecipe(0), recipe);
    assertEquals(list.getRecipe(1), recipe2);
    assertEquals(list.getRecipe(2), recipe3);
  }

  @Test void getRecipeBoundary()
  {
    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getRecipe(-1);
    });

    list.addRecipe(recipe);
    list.addRecipe(recipe2);
    list.addRecipe(recipe3);
    assertEquals(list.getRecipe(0), recipe);
    assertEquals(list.getRecipe(1), recipe2);
    assertEquals(list.getRecipe(2), recipe3);

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getRecipe((Integer.MAX_VALUE));
    });
  }

  @Test void getRecipeExceptions()
  {
    list.addRecipe(recipe);
    list.addRecipe(recipe2);
    list.addRecipe(recipe3);

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getRecipe(-100);
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.getRecipe(175);
    });
  }

  @Test void getSizeZero()
  {
    assertEquals(0, list.getSize());
  }

  @Test void getSizeOne()
  {
    list.addRecipe(recipe);
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

  @Test void getRecipeByIdZero()
  {
    assertNull(populatedList.getRecipeById(6));
  }

  @Test void getRecipeByIdOne()
  {
    assertEquals(recipe, populatedList.getRecipeById(1));
  }

  @Test void getRecipeByIdMany()
  {
    assertEquals(recipe, populatedList.getRecipeById(1));
    assertEquals(recipe2, populatedList.getRecipeById(5));
    assertEquals(recipe3, populatedList.getRecipeById(7));
  }

  @Test void getRecipeByIdBoundary()
  {
    assertNull(populatedList.getRecipeById(-1));
    assertEquals(recipe, populatedList.getRecipeById(1));
    assertEquals(recipe2, populatedList.getRecipeById(5));
    assertEquals(recipe3, populatedList.getRecipeById(7));
    assertNull(populatedList.getRecipeById(10001));
  }

  @Test void getRecipeByIdExceptions()
  {
    // not possible
  }
}