package model;

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
    StringBuilder string = new StringBuilder("recipes: {");

    for (String recipeIds : recipeIds)
    {
      string.append(recipeIds).append(", ");
    }

    string.append("}");

    return string.toString();
  }
}