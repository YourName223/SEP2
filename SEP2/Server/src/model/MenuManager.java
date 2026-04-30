package model;

import Repository.MenuDAO;
import Repository.MenuDAOImpl;

import java.util.ArrayList;

public class MenuManager
{
  ArrayList<MenuItem> menuItems;
  MenuDAOImpl menuDAO;

  public MenuManager()
  {
    menuItems = new ArrayList<>();
    try
    {
      menuDAO = new MenuDAOImpl();
    }
    catch (Exception e)
    {

    }
  }

  public void addMenuItem(String name, String allergies, double price)
  {
    if(name == null || allergies == null || name.isBlank())
    {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    menuItems.add(new MenuItem(name,allergies,price));
  }

  public ArrayList<MenuItem> getMenuItems()
  {
    ArrayList<MenuItem> menuItems = new ArrayList<>();

    for(String name : menuDAO.getAllNames())
    {
      try
      {
        menuItems.add(menuDAO.readByName(name).get(0));
      }
      catch (Exception e)
      {

      }
    }

    return menuItems;
  }

  public MenuItem getMenuItem(int index)
  {
    if(menuItems.size() >= index)
      return menuItems.get(index);
    throw new IllegalArgumentException("index is out of bounds");
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
