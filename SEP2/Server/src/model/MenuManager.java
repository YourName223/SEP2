package model;

import java.util.ArrayList;

public class MenuManager
{
  ArrayList<MenuItem> menuItems;

  public MenuManager()
  {
    menuItems = new ArrayList<>();
  }

  public void addMenuItem(int id, String name, String allergies, double price)
  {
    menuItems.add(new MenuItem(id,name,allergies,price));
  }

  public ArrayList<MenuItem> getMenuItems()
  {
    return menuItems;
  }

  public MenuItem getMenuItem(int index)
  {
    return menuItems.get(index);
  }

  public void addProductToMenuItem(int index, Component product)
  {
    menuItems.get(index).addProduct(product);
  }

  public String menuItemToString(int index)
  {
    return menuItems.get(index).toString();
  }
}
