package model;

import Repository.MenuDAOImpl;
import java.util.ArrayList;

public class RecipeManager
{
  private MenuDAOImpl menuDAO;
  private ArrayList<Recipe> recipes;

  public RecipeManager()
  {
    recipes = new ArrayList<>();
    try
    {
      menuDAO = MenuDAOImpl.getInstance();
      getRecipesFromDatabase();
    }
    catch (Exception e){}
  }

  public ArrayList<String> getRecipeIdFromMenuItem(MenuItem menuItem)
  {
    ArrayList<String> recipeIds = new ArrayList<>();
    for (Recipe recipe : menuItem.getRecipes())
    {
      recipeIds.add(recipe.getId());
    }
    return recipeIds;
  }

  private void getRecipesFromDatabase()
  {
    for(String menuName : menuDAO.getAllMenuNames())
    {
      for(String recipeId : menuDAO.getRecipeIdsFromMenuItemName(menuName))
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

  /*
  public Recipe getRecipe(String recipeId)
  {
    return menuDAO.getRecipeWithIngredients(recipeId);
  }
  Can be usefull later
   */
}
