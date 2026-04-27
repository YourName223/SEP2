package model;

import java.util.ArrayList;

public class MenuItem
{
  private int id;
  private String name;
  private String allergies;
  private double price;
  private ArrayList<Component> products;

  public MenuItem(int id, String name, String allergies, double price)
  {
    this.id = id;
    this.name = name;
    this.allergies = allergies;
    this.price = price;
    products = new ArrayList<>();
  }

  public int getId() { return id; }
  public String getName() { return name; }
  public String getAllergies() { return allergies; }
  public double getPrice() { return price; }
  public ArrayList<Component> getProducts()
  {
    return products;
  }

  public void setName(String name) { this.name = name; }
  public void setAllergies(String allergies) { this.allergies = allergies; }
  public void setPrice(double price) { this.price = price; }
  public void addProduct(Component product)
  {
    products.add(product);
  }
}
