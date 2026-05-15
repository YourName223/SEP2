package model;

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
      menuDAO = MenuDAOImpl.getInstance();
      getMenuItemsFromDatabase();
    }
    catch (Exception e)
    {

    }
  }

  private void getMenuItemsFromDatabase()
  {
    for(String name : menuDAO.getAllMenuNames())
    {
      try
      {
        menuItems.add(menuDAO.readByName(name).getFirst());
      }
      catch (Exception e)
      {

      }
    }
  }

  public ArrayList<MenuItem> getMenuItems()
  {
    return menuItems;
  }

  public MenuItem getMenuItemById(String id)
  {
    for(MenuItem menuItem : menuItems)
    {
      if(menuItem.getName().equals(id))
      {
        return menuItem;
      }
    }
    return null;
  }

  public MenuItem getMenuItem(int index)
  {
    if(menuItems.size() >= index)
      return menuItems.get(index);
    throw new IllegalArgumentException("index is out of bounds");
  }
/*
  public void addProductToMenuItem(int index, Component product)
  {
    menuItems.get(index).addProduct(product);
  }
  public String menuItemToString(int index)
  {
    return menuItems.get(index).toString();
  }
  can be useful later*/

}
