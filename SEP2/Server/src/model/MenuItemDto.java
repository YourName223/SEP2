package model;

import java.util.List;

public class MenuItemDto
{
  public String name;
  public String allergies;
  public double price;
  public List<String> recipeIds;

  public MenuItemDto(String name, String allergies, double price, List<String> recipeIds)
  {
    this.name = name;
    this.allergies = allergies;
    this.price = price;
    this.recipeIds = recipeIds;
  }
}
