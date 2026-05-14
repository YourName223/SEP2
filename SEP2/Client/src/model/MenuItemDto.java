package model;

import java.time.Duration;
import java.util.ArrayList;

public class MenuItemDto
{
  private String name;
  private String allergies;
  private double price;
  private ArrayList<String> recipeIds;
  private int stock;
  private Duration prepTime;

  public MenuItemDto(String name, String allergies, double price, ArrayList<String> recipeIds, int stock, Duration prepTime)
  {
    this.name = name;
    this.allergies = allergies;
    this.price = price;
    this.recipeIds = recipeIds;
    this.stock = stock;
    this.prepTime = prepTime;
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

  public Duration getPrepTime()
  {
    return prepTime;
  }
}