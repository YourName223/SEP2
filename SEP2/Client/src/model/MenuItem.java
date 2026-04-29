package model;

import java.util.ArrayList;

public class MenuItem
{
  private String name;
  private String allergies;
  private double price;
  private ArrayList<Component> products;

  public MenuItem(String name, String allergies, double price)
  {
    this.name = name;
    this.allergies = allergies;
    this.price = price;
    products = new ArrayList<>();
  }

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
  public void removeProduct(Component product)
  {
    products.remove(product);
  }

  public String toString()
  {
    StringBuilder string = new StringBuilder();
    string.append("Name:").append(name).append("Allergies:").append(allergies).append("Price:").append(price).append("Products:");

    string.append("{");

    for (Component component : products)
    {
      string.append(component.getName()).append(",");
    }

    if (products.size() > 0)
      string.setLength(string.length() - 1);

    string.append("}");

    return string.toString();
  }
}
