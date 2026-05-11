package model;

public class Ingredient
{
  private String id;
  private String name;
  private double amount;

  public Ingredient(String id, String name, double amount)
  {
    this.id = id;
    this.name = name;
    this.amount = amount;
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
}