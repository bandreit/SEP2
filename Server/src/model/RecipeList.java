//package model;
//
//import java.util.ArrayList;
//
//public class RecipeList
//{
//  private ArrayList<Recipe> recipes;
//
//  public RecipeList()
//  {
//    this.recipes = new ArrayList<>();
//  }
//
//  public int getSize()
//  {
//    return recipes.size();
//  }
//
//  public Recipe getStudent(int index)
//  {
//    return recipes.get(index);
//  }
//
//  public void addStudent(Recipe recipe)
//  {
//    if (recipe == null)
//    {
//      throw new IllegalArgumentException("A null model.Student");
//    }
//    if (getStudentByNumber(recipe.getStudyNumber()) != null)
//    {
//      throw new IllegalArgumentException(
//          "model.Student with study number " + recipe.getStudyNumber()
//              + " already exist");
//    }
//    recipes.add(recipe);
//  }
//
//  public Recipe getStudentByNumber(String studyNumber)
//  {
//    if (studyNumber != null)
//    {
//      for (int i = 0; i < recipes.size(); i++)
//      {
//        if (studyNumber.equalsIgnoreCase(recipes.get(i).getStudyNumber()))
//        {
//          return recipes.get(i);
//        }
//      }
//    }
//    return null;
//  }
//
//  public Recipe getStudentByName(String name)
//  {
//    if (name != null)
//    {
//      for (int i = 0; i < recipes.size(); i++)
//      {
//        if (name.equalsIgnoreCase(recipes.get(i).getName()))
//        {
//          return recipes.get(i);
//        }
//      }
//    }
//    return null;
//  }
//}
