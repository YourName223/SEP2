package model;

public class Ingredient
{
  private String id;
  private String name;
  private double stock;

  public Ingredient(String id, String name, double stock)
  {
    this.id = id;
    this.name = name;
    this.stock = stock;
  }

  public String getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public double getStock() { return stock; }

  public void setStock(double stock) { this.stock = stock; }
}