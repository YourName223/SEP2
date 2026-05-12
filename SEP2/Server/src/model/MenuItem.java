package model;

import java.util.ArrayList;

public class MenuItem
{
  private String name;
  private String allergies;
  private double price;
  private ArrayList<Recipe> recipes;

  public MenuItem(String name, String allergies, double price, ArrayList<Recipe> recipes)
  {
    this.name = name;
    this.allergies = allergies;
    this.price = price;
    this.recipes = recipes;
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

  public ArrayList<Recipe> getRecipes()
  {
    return recipes;
  }

  public String recipesString()
  {
    StringBuilder string = new StringBuilder("[");

    for (Recipe recipe : recipes)
    {
      string.append(recipe.getName());
      string.append(" (");

      for(Ingredient ingredient :recipe.getIngredients())
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
  public int getStock()
  {
    int minStock = Integer.MAX_VALUE;

    for (Recipe recipe : recipes)
    {
      for (Ingredient ingredient : recipe.getIngredients())
      {
        if (ingredient.getAmount() > 0)
        {
          int possible = (int) (ingredient.getStock() / ingredient.getAmount());
          minStock = Math.min(minStock, possible);
        }
      }
    }

    return minStock == Integer.MAX_VALUE ? 0 : minStock;
  }
}