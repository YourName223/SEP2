package model;

public class Ingredient
{
  private String id;
  private String name;
  private double amount;
  private double stock;

  public Ingredient(String id, String name, double amount, double stock)
  {
    this.id = id;
    this.name = name;
    this.amount = amount;
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

  public double getAmount()
  {
    return amount;
  }

  public void setAmount(double amount)
  {
    this.amount = amount;
  }

  public double getStock() { return stock; }

  public void setStock(double stock) { this.stock = stock; }
}