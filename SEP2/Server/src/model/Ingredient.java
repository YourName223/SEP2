package model;

public class Ingredient
{
  private String id;
  private String name;
  private int stock;

  public void setId(String id)
  {
    this.id = id;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public void setId(int stock)
  {
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