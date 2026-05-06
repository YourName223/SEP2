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
      saveFromDataBase();
    }
    catch (Exception e)
    {

    }
  }

  private void saveFromDataBase()
  {
    for(String name : menuDAO.getAllNames())
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

  public ArrayList<MenuItemDto> getMenuItemsDto()
  {
    ArrayList<MenuItemDto> menuItemsDTO = new ArrayList<>();

    for(MenuItem menuItem : getMenuItems())
    {
      try
      {
        menuItemsDTO.add(new MenuItemDto(menuItem.getName(),menuItem.getAllergies(),menuItem.getPrice(),menuItem.getRecipeIds()));
      }
      catch (Exception e)
      {

      }
    }

    return menuItemsDTO;
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
can be useful later*/
  public String menuItemToString(int index)
  {
    return menuItems.get(index).toString();
  }
}
