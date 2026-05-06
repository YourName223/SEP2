package model;

public class Ingredient
{
  private String id;
  private String name;
  private int stock;

  public Ingredient(String id, String name, int stock)
  {
    this.id = id;
    this.name = name;
    this.stock = stock;
  }

  public String getName()
  {
    return name;
  }

  public String getId()
  {
    return id;
  }

  public int getStock()
  {
    return stock;
  }
}