package model;

import mediator.Client;
import mediator.ClientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LocalModelTest
{
  private LocalModel localModel;
  private ClientModel clientModel;
  private ListOfIngredients listOfIngredients;
  private Ingredient ingredient;

  @BeforeEach void setUp() throws IOException, NotBoundException
  {
    localModel = new LocalModelManager();
    clientModel = new Client(localModel);
    listOfIngredients = new ListOfIngredients();
    ingredient = new Ingredient(1, "Water", 10, "ml");
    listOfIngredients.addIngredient(ingredient);
    User.getInstance(1);
  }

  @Test void loginZero() throws Exception
  {
    // no need to test
  }

  @Test void loginOne() throws Exception
  {
    assertEquals(1, localModel.login("bandrei", "123456"));
  }

  @Test void loginMany() throws Exception
  {
    assertEquals(1, localModel.login("bandrei", "123456"));
    assertEquals(2, localModel.login("aa", "123456"));
  }

  @Test void loginBoundary()
  {
    // can't test
  }

  @Test void loginExceptions()
  {
    assertThrows(IllegalStateException.class, () -> {
      localModel.login("bandrei1", "123456");
    });
  }

  @Test void registerZero()
  {
    // no need to test
  }

  @Test void registerOne() throws RemoteException, SQLException
  {
    int userFromDB = localModel
        .register("admin8", "123456", "admin@gmail.com", "123456");
    assertEquals(8, userFromDB);
  }

  @Test void registerMany() throws RemoteException, SQLException
  {
    int userFromDB = localModel
        .register("admin9", "123456", "admin@gmail.com", "123456");
    int userFromDB2 = localModel
        .register("admin10", "123456", "admin@gmail.com", "123456");
    assertEquals(9, userFromDB);
    assertEquals(10, userFromDB2);
  }

  @Test void registerBoundary() throws RemoteException, SQLException
  {
    // can't be tested
  }

  @Test void registerExceptions() throws RemoteException, SQLException
  {
    assertThrows(IllegalStateException.class, () -> {
      localModel.register("bandrei", "123456", "admin@gmail.com", "123456");
    });
  }

  @Test void createRecipeZero()
  {
    // no need to test
  }

  @Test void createRecipeOne() throws Exception
  {
    Recipe recipe = localModel
        .createRecipe("Steak", "Juicy Steak", listOfIngredients,
            "Some instructions.", 10, "Lunch");
    assertEquals("Steak", recipe.getRecipeName());
    assertEquals("Juicy Steak", recipe.getDescription());
    assertEquals("Some instructions.", recipe.getInstructions());
    assertEquals(10, recipe.getPreparationTime());
    assertEquals("Lunch", recipe.getCategory());
  }

  @Test void createRecipeMany() throws Exception
  {
    Recipe recipe = localModel
        .createRecipe("Steak", "Juicy Steak", listOfIngredients,
            "Some instructions.", 10, "Lunch");
    assertEquals("Steak", recipe.getRecipeName());
    assertEquals("Juicy Steak", recipe.getDescription());
    assertEquals("Some instructions.", recipe.getInstructions());
    assertEquals(10, recipe.getPreparationTime());
    assertEquals("Lunch", recipe.getCategory());

    Recipe recipe2 = localModel
        .createRecipe("Chicken", "Juicy Chicken", listOfIngredients,
            "Some instructions.", 20, "Dinner");
    assertEquals("Chicken", recipe2.getRecipeName());
    assertEquals("Juicy Chicken", recipe2.getDescription());
    assertEquals("Some instructions.", recipe2.getInstructions());
    assertEquals(20, recipe2.getPreparationTime());
    assertEquals("Dinner", recipe2.getCategory());
  }

  @Test void createRecipeBoundary()
  {
    // can't be tested
  }

  @Test void createRecipeExceptions()
  {
    // can't be tested
  }

  @Test void editRecipeZero()
  {
    // no need to test
  }

  @Test void editRecipeOne() throws Exception
  {
    Recipe recipe = localModel
        .editRecipe(2, "Steak", "Juicy Steak", listOfIngredients,
            "Some instructions.", 10, "Lunch");
    assertEquals("Steak", recipe.getRecipeName());
    assertEquals(2, recipe.getId());
    assertEquals("Juicy Steak", recipe.getDescription());
    assertEquals("Some instructions.", recipe.getInstructions());
    assertEquals(10, recipe.getPreparationTime());
    assertEquals("Lunch", recipe.getCategory());
    assertEquals(1, recipe.getOwnerId());
  }

  @Test void editRecipeMany() throws Exception
  {
    Recipe recipe = localModel
        .editRecipe(2, "Steak", "Juicy Steak", listOfIngredients,
            "Some instructions.", 10, "Lunch");
    assertEquals("Steak", recipe.getRecipeName());
    assertEquals(2, recipe.getId());
    assertEquals("Juicy Steak", recipe.getDescription());
    assertEquals("Some instructions.", recipe.getInstructions());
    assertEquals(10, recipe.getPreparationTime());
    assertEquals("Lunch", recipe.getCategory());
    assertEquals(1, recipe.getOwnerId());

    Recipe recipe2 = localModel
        .editRecipe(1, "Chicken", "Juicy Chicken", listOfIngredients,
            "Some instructions.", 10, "Dinner");
    assertEquals("Chicken", recipe2.getRecipeName());
    assertEquals(1, recipe2.getId());
    assertEquals("Juicy Chicken", recipe2.getDescription());
    assertEquals("Some instructions.", recipe2.getInstructions());
    assertEquals(10, recipe2.getPreparationTime());
    assertEquals("Dinner", recipe2.getCategory());
    assertEquals(1, recipe2.getOwnerId());
  }

  @Test void editRecipeBoundary()
  {
    // can't test
  }

  @Test void editRecipeExceptions()
  {
    assertThrows(UnexpectedException.class, () -> {
      Recipe recipe = clientModel
          .editRecipe(69, "Steak", "Juicy Steak", listOfIngredients,
              "Some instructions.", 10, "Lunch", 1);
    });
  }

  @Test void getRecipesForUserZero() throws RemoteException, SQLException
  {
    assertEquals(0, clientModel.getRecipesForUser(0).getSize());
  }

  @Test void getRecipesForUserOne() throws Exception
  {
    RecipeList recipes = localModel.getRecipes();
    int recipesForUser1 = 0;
    for (int i = 0; i < recipes.getSize(); i++)
    {
      if (recipes.getRecipe(i).getOwnerId() == 1 )
      {
        recipesForUser1++;
      }
    }
    assertEquals(recipesForUser1, localModel.getRecipesForUser().getSize());
  }

  @Test void getRecipesForUserMany() throws Exception
  {
    RecipeList recipes = localModel.getRecipes();
    int recipesForUser1 = 0;
    int recipesForUser2 = 0;
    for (int i = 0; i < recipes.getSize(); i++)
    {
      if (recipes.getRecipe(i).getOwnerId() == 1 )
      {
       recipesForUser1++;
      } else if (recipes.getRecipe(i).getOwnerId() == 2)
      {
        recipesForUser2++;
      }
    }
    assertEquals(recipesForUser2, clientModel.getRecipesForUser(2).getSize());
    assertEquals(recipesForUser1, clientModel.getRecipesForUser(1).getSize());
  }

  @Test void getRecipesForUserBoundary()
  {
    // can't test
  }

  @Test void getRecipesForUserExceptions()
  {
    // can't test
  }

  @Test void getIngredientsForRecipeZero() throws RemoteException, SQLException
  {
    assertEquals(0, localModel.getIngredientsForRecipe(0).getSize());
  }

  @Test void getIngredientsForRecipeOne() throws Exception
  {
    assertEquals(2, localModel.getIngredientsForRecipe(1).getSize());
  }

  @Test void getIngredientsForRecipeMany() throws Exception
  {
    assertEquals(2, localModel.getIngredientsForRecipe(1).getSize());
    assertEquals(1, localModel.getIngredientsForRecipe(2).getSize());
  }

  @Test void getIngredientsForRecipeBoundary()
      throws SQLException, RemoteException
  {
    assertEquals(0, localModel.getIngredientsForRecipe(-1).getSize());
    assertEquals(0, localModel.getIngredientsForRecipe(10001).getSize());
  }

  @Test void getIngredientsForRecipeExceptions()
  {
    // can't test
  }

  @Test void getRecipesZero() throws RemoteException, SQLException
  {
    // no need to test
  }

  @Test void getRecipesOne() throws Exception
  {
    assertEquals(localModel.getRecipes(), localModel.getSavedRecipeList());
  }

  @Test void getRecipesMany() throws Exception
  {
    assertEquals(localModel.getRecipes(), localModel.getSavedRecipeList());
    assertEquals(localModel.getRecipes(), localModel.getSavedRecipeList());
    assertEquals(localModel.getRecipes(), localModel.getSavedRecipeList());
  }

  @Test void getRecipesBoundary() throws SQLException, RemoteException
  {
    // can't test
  }

  @Test void getRecipesExceptions()
  {
    // can't test
  }

  @Test void searchRecipesZero() throws RemoteException, SQLException
  {
    assertEquals(0, localModel.searchRecipes(null).getSize());
  }

  @Test void searchRecipesOne() throws Exception
  {
    localModel.getRecipes();
    int found = 0;
    for (int i = 0; i < localModel.getSavedRecipeList().getSize(); i++)
    {
      if (localModel.getSavedRecipeList().getRecipe(i).getRecipeName()
          .matches(".*Chicken.*"))
      {
        found++;
      }
    }

    assertEquals(found, localModel.searchRecipes("chicken").getSize());
  }

  @Test void searchRecipesMany() throws Exception
  {
    localModel.getRecipes();
    int found = 0;
    for (int i = 0; i < localModel.getSavedRecipeList().getSize(); i++)
    {
      if (localModel.getSavedRecipeList().getRecipe(i).getRecipeName()
          .matches(".*Steak.*"))
      {
        found++;
      }
    }

    assertEquals(found, localModel.searchRecipes("steak").getSize());

    localModel.getRecipes();
    int found2 = 0;
    for (int i = 0; i < localModel.getSavedRecipeList().getSize(); i++)
    {
      if (localModel.getSavedRecipeList().getRecipe(i).getRecipeName()
          .matches(".*Steak.*"))
      {
        found2++;
      }
    }

    assertEquals(found2, localModel.searchRecipes("steak").getSize());
  }

  @Test void searchRecipesBoundary() throws SQLException, RemoteException
  {
    // can't test
  }

  @Test void searchRecipesExceptions()
  {
    // can't test
  }

  @Test void addFullIngredientWithQtyAndAMeasurementZero()
  {
    // ViewModel doesn't allow this
    localModel.addFullIngredientWithQtyAndAMeasurement(
        new Ingredient(1, "Juice", 0, "ml"));
    assertEquals(1, localModel.getListOfIngredients().getSize());
  }

  @Test void addFullIngredientWithQtyAndAMeasurementOne()
  {
    localModel.addFullIngredientWithQtyAndAMeasurement(ingredient);
    assertEquals(1, localModel.getListOfIngredients().getSize());
  }

  @Test void addFullIngredientWithQtyAndAMeasurementMany()
  {
    localModel.addFullIngredientWithQtyAndAMeasurement(ingredient);
    assertEquals(1, localModel.getListOfIngredients().getSize());
    localModel.addFullIngredientWithQtyAndAMeasurement(
        new Ingredient(1, "Juice", 10, "ml"));
    assertEquals(2, localModel.getListOfIngredients().getSize());
  }

  @Test void addFullIngredientWithQtyAndAMeasurementBoundary()
  {
    assertEquals(0, localModel.getListOfIngredients().getSize());
  }

  @Test void addFullIngredientWithQtyAndAMeasurementExceptions()
  {
    // no need for test
  }

  @Test void deleteRecipeZero() throws RemoteException, SQLException
  {
    assertThrows(SQLException.class, () -> {
      localModel.deleteRecipe(0);
    });
  }

  @Test void deleteRecipeOne() throws RemoteException, SQLException
  {
    localModel.createRecipe("Steak", "Juicy Steak", listOfIngredients,
        "Some instructions.", 10, "Lunch");
    localModel.getRecipes();
    int numberOfRecipesBeforeDelete = localModel.getRecipes().getSize();
    int idOfTheLastOne = localModel.getRecipes()
        .getRecipe(localModel.getRecipes().getSize() - 1).getId();

    localModel.deleteRecipe(idOfTheLastOne);
    assertEquals(numberOfRecipesBeforeDelete - 1,
        localModel.getRecipes().getSize());
  }

  @Test void deleteRecipeMany() throws RemoteException, SQLException
  {
    localModel.createRecipe("Steak", "Juicy Steak", listOfIngredients,
        "Some instructions.", 10, "Lunch");
    localModel.getRecipes();
    int numberOfRecipesBeforeDelete = localModel.getRecipes().getSize();
    int idOfTheLastOne = localModel.getRecipes()
        .getRecipe(localModel.getRecipes().getSize() - 1).getId();

    localModel.deleteRecipe(idOfTheLastOne);
    assertEquals(numberOfRecipesBeforeDelete - 1,
        localModel.getRecipes().getSize());

    localModel.createRecipe("Steak", "Juicy Steak", listOfIngredients,
        "Some instructions.", 10, "Lunch");
    localModel.getRecipes();
    numberOfRecipesBeforeDelete = localModel.getRecipes().getSize();
    idOfTheLastOne = localModel.getRecipes()
        .getRecipe(localModel.getRecipes().getSize() - 1).getId();

    localModel.deleteRecipe(idOfTheLastOne);
    assertEquals(numberOfRecipesBeforeDelete - 1,
        localModel.getRecipes().getSize());
  }

  @Test void deleteRecipeBoundary()
  {
    // no need for test
  }

  @Test void deleteRecipeExceptions()
  {
    // no need for test
  }

  @Test void createCommentZero() throws SQLException, RemoteException
  {
    // no need for test
  }

  @Test void createCommentOne() throws SQLException, RemoteException
  {
    localModel.createComment(3, 1, "A comment.");
    assertEquals("bandrei: bandrei: A comment.\n", localModel.getComment(7));
  }

  @Test void createCommentMany() throws SQLException, RemoteException
  {
    localModel.createComment(5, 1, "A comment.");
    assertEquals("bandrei: A comment.\n", localModel.getComment(6));

    localModel.createComment(5, 1, "A comment.");
    assertEquals("bandrei: A comment.\n",
        localModel.getComment(6));
  }

  @Test void createCommentBoundaries() throws SQLException, RemoteException
  {
    // no need to test
  }

  @Test void createCommentManyExceptions() throws SQLException, RemoteException
  {
    // no need for test
  }
}