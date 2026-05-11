package model;

public class Ingredient
{
  private String id;
  private String name;
  private int amount;

  public Ingredient(String id, String name, int stock)
  {
    this.id = id;
    this.name = name;
    this.amount = stock;
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

  public int getAmount()
  {
    return amount;
  }

  public void setAmount(int amount)
  {
    this.amount = amount;
  }
}