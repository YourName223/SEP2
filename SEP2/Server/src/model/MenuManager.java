package model;

import Repository.MenuDAOImpl;

import java.util.ArrayList;

public class MenuManager
{
  ArrayList<MenuItem> menuItems;
  ArrayList<Recipe> recipes;
  MenuDAOImpl menuDAO;

  public MenuManager()
  {
    menuItems = new ArrayList<>();
    recipes = new ArrayList<>();
    try
    {
      menuDAO = MenuDAOImpl.getInstance();
      getMenuItemsFromDatabase();
      getRecipesFromDatabase();
    }
    catch (Exception e)
    {

    }
  }

  private void getMenuItemsFromDatabase()
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

  private void getRecipesFromDatabase()
  {
    for(MenuItem menuItem : menuItems)
    {
      for(String recipeId : menuDAO.getRecipeIdsFromMenuItem(menuItem.getName()))
      {
        try
        {
          recipes.add(menuDAO.getRecipeWithIngredients(recipeId));
        }
        catch (Exception e)
        {

        }
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

  public ArrayList<String> getIngredientNamesFromRecipeId(String recipeId)
  {
    ArrayList<String> ingredientNames = new ArrayList<>();

    for (Recipe recipe : recipes)
    {
      if (recipe.getId().equals(recipeId))
      {
        ingredientNames.add(recipe.getName());
      }
    }
    return ingredientNames;
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
