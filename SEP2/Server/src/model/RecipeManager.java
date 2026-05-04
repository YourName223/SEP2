package model;

import Repository.MenuDAOImpl;

import java.sql.SQLException;

public class RecipeManager
{
  private MenuDAOImpl menuDAO;

  public RecipeManager() throws SQLException
  {
    menuDAO = new MenuDAOImpl();
  }

  public Recipe getRecipe(String recipeId)
  {
    return menuDAO.getRecipeWithIngredients(recipeId);
  }
}
