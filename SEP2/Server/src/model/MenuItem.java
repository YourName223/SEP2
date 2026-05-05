package model;

import Repository.MenuDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class MenuItem
{
  private String name;
  private String allergies;
  private double price;
  private ArrayList<String> recipeIds;

  public MenuItem(String name, String allergies, double price, ArrayList<String> recipeIds)
  {
    this.name = name;
    this.allergies = allergies;
    this.price = price;
    this.recipeIds = recipeIds;
  }

  public String getName()
  {
    return name;
  }

  public String getAllergies()
  {
    return allergies;
  }

  public double getPrice()
  {
    return price;
  }

  public ArrayList<String> getRecipeIds()
  {
    return recipeIds;
  }

  public String recipesString()
  {
    StringBuilder string = new StringBuilder("[");

    MenuDAOImpl menuDAO = null;

    try{
      menuDAO = MenuDAOImpl.getInstance();
    }
    catch (Exception e){}

    for (String recipeIds : recipeIds)
    {
      string.append(menuDAO.getRecipeWithIngredients(recipeIds).getName());
      string.append(" (");
      System.out.println(recipeIds);
      System.out.println(menuDAO.getAllIngredientsFromRecipe(recipeIds));

      for(Ingredient ingredient :menuDAO.getRecipeWithIngredients(recipeIds).getIngredients())
      {
        string.append(ingredient.getName()).append(", ");
      }
        if (string.length() > 1)
          string.setLength(string.length() - 2);
      string.append("), ");
    }
    if (string.length() > 1)
      string.setLength(string.length() - 2);
    string.append("]");

    return string.toString();
  }
}