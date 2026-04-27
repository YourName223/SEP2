package model;

public class Menu
{
  private int id;
  private String name;
  private String allergies;
  private double price;

  public Menu(int id, String name, String allergies, double price)
  {
    this.id = id;
    this.name = name;
    this.allergies = allergies;
    this.price = price;
  }

  // Getters
  public int getId() { return id; }
  public String getName() { return name; }
  public String getAllergies() { return allergies; }
  public double getPrice() { return price; }

  // Setters
  public void setName(String name) { this.name = name; }
  public void setAllergies(String allergies) { this.allergies = allergies; }
  public void setPrice(double price) { this.price = price; }
}