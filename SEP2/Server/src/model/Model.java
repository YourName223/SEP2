package model;

import java.util.ArrayList;

public interface Model
{
  public void receiveOrder(Order order);

  public ArrayList<MenuItem> getMenuItems();

  public Component createComponent(String name);

  public Product createProduct(String name);

  public Component addProductToComponent(Component product, Component component);

  public void addMenuItem(String name, String allergies, double price);

  public void addProductToMenuItem(int index, Component product);

  public String menuItemToString(int index);
}
