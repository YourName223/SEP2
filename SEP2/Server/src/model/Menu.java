package model;

import java.util.ArrayList;

public class Menu
{
  ArrayList<MenuItem> menuItems;

  public Menu()
  {
    menuItems = new ArrayList<>();
  }

  public void addMenuItem(int id, String name, String allergies, double price)
  {
    menuItems.add(new MenuItem(id, name, allergies, price));
  }

  public ArrayList<MenuItem> getMenuItems()
  {
    return menuItems;
  }

  public MenuItem getMenu(int index)
  {
    return menuItems.get(index);
  }

  public void addProductToMenuItem(int index, Component product)
  {
    getMenu(index).addProduct(product);
  }
}