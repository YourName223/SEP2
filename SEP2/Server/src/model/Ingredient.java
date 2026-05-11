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

  public Ingredient(Ingredient ingredient, int amount)
  {
    this.id = ingredient.getId();
    this.name = ingredient.getName();
    this.amount = amount;
  }

  public String getName()
  {
    return name;
  }

  public String getId()
  {
    return id;
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