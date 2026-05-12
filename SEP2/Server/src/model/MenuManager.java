package model;

import Repository.MenuDAOImpl;
import java.util.ArrayList;

public class MenuManager
{
  ArrayList<MenuItem> menuItems;
  ArrayList<MenuItemDto> menuItemsDto;
  MenuDAOImpl menuDAO;

  public MenuManager()
  {
    menuItems = new ArrayList<>();
    menuItemsDto = new ArrayList<>();

    try
    {
      menuDAO = MenuDAOImpl.getInstance();
      getMenuItemsFromDatabase();
      getMenuItemsDtoFromDatabase();
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

  private void getMenuItemsDtoFromDatabase()
  {
    for(MenuItem menuItem : getMenuItems())
    {
      ArrayList<String> recipeId = new ArrayList<>();

      for(Recipe recipe : menuItem.getRecipes())
      {
        recipeId.add(recipe.getId());
      }

      try
      {
        menuItemsDto.add(new MenuItemDto(menuItem.getName(),menuItem.getAllergies(),menuItem.getPrice(),recipeId,menuItem.getStock()));
      }
      catch (Exception e)
      {

      }
    }
  }

  public ArrayList<MenuItemDto> getMenuItemsDto()
  {
    return menuItemsDto;
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
