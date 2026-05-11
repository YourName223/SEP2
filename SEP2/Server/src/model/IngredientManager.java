package model;

import Repository.MenuDAOImpl;

import java.util.ArrayList;

public class IngredientManager
{
  MenuDAOImpl menuDAO;
  ArrayList<Ingredient> stock;

  IngredientManager()
  {
    try
    {
      menuDAO = MenuDAOImpl.getInstance();
    }
    catch (Exception e)
    {

    }
    stock = menuDAO.getStock();
  }

  public boolean hasStockForIngredients(ArrayList<Ingredient> ingredients)
  {
    for(Ingredient ingredientOrder : ingredients)
    {
      boolean found = false;

      for(Ingredient ingredientStock : ingredients)
      {
        if (ingredientStock.getId().equals(ingredientOrder.getId()) && ingredientStock.getAmount() >= ingredientOrder.getAmount())
        {
          System.out.println(ingredientStock.getName() + ingredientStock.getAmount() + "Spørger om" + ingredientOrder.getName() + ingredientOrder.getAmount());
          found = true;
          break;
        }
      }

      if(!found)
      {
        return false;
      }
    }
    return true;
  }

  public ArrayList<Ingredient> getIngredientsInOrder(Order order)
  {
    ArrayList<Ingredient> ingredientsInOrder = new ArrayList<>();

    for (OrderItem orderItem : order.getOrderItems())
    {
      for (Recipe recipe : orderItem.getItem().getRecipes())
      {
        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients())
        {
          addIngredientToList(ingredientsInOrder,recipeIngredient);
        }
      }
    }

    return ingredientsInOrder;
  }

  private void addIngredientToList(ArrayList<Ingredient> ingredientList, RecipeIngredient recipeIngredient)
  {
    for (Ingredient ingredient1 : ingredientList)
    {
      if (ingredient1.getId().equals(recipeIngredient.getIngredient().getId()))
      {
        ingredient1.setAmount(ingredient1.getAmount()+recipeIngredient.getIngredient().getAmount());
        break;
      }
    }
    ingredientList.add(recipeIngredient.getIngredient());
  }

  public void removeIngredients(ArrayList<Ingredient> ingredientsInOrder)
  {
    for (Ingredient ingredient : stock)
    {
      for (Ingredient ingredientInOrder : ingredientsInOrder)
      {
        if (ingredient.getId().equals(ingredientInOrder.getId()))
        {
          int amount = ingredient.getAmount()-ingredientInOrder.getAmount();
          menuDAO.setAmountOnIngredient(ingredient.getId(),ingredientInOrder.getAmount());
          ingredient.setAmount(amount);
        }
      }
    }
  }

  public void addIngredients(ArrayList<Ingredient> ingredientsInOrder)
  {
    for (Ingredient ingredient : stock)
    {
      for (Ingredient ingredientInOrder : ingredientsInOrder)
      {
        if (ingredient.getId().equals(ingredientInOrder.getId()))
        {
          int amount = ingredient.getAmount()+ingredientInOrder.getAmount();
          menuDAO.setAmountOnIngredient(ingredient.getId(),-ingredientInOrder.getAmount());
          ingredient.setAmount(amount);
        }
      }
    }
  }
}
