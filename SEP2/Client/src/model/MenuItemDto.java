package model;

import java.util.List;

public class MenuItemDto
{
  private String name;
  private String allergies;
  private double price;
  private List<String> recipeIds;

  public MenuItemDto(String name, String allergies, double price, List<String> recipeIds)
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
}