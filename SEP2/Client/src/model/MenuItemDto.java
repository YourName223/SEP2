package model;

import java.util.ArrayList;

public class MenuItemDto
{
  private String name;
  private String allergies;
  private double price;
  private ArrayList<String> recipeIds;
  private int stock;

  public MenuItemDto(String name, String allergies, double price, ArrayList<String> recipeIds, int stock)
  {
    this.name = name;
    this.allergies = allergies;
    this.price = price;
    this.recipeIds = recipeIds;
    this.stock = stock;
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

  public int getStock()
  {
    return stock;
  }
}