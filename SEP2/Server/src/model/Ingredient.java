package model;

public class Ingredient
{
  private int id;
  private String name;
  private String allergen; // or boolean flags later

  public Ingredient(int id, String name, String allergen)
  {
    this.id = id;
    this.name = name;
    this.allergen = allergen;
  }

  public int getId() { return id; }
  public String getName() { return name; }
  public String getAllergen() { return allergen; }
}
