package model;

public class RecipeManager
{
  private RecipeDAO recipeDAO;

  public RecipeManager()
  {
    recipeDAO = new RecipeDAOImpl();
  }

  public Recipe getRecipe(String recipeId)
  {
    return recipeDAO.getRecipeWithIngredients(recipeId);
  }
}
