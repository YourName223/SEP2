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

  public boolean hasStockForRecipeIngredients(ArrayList<RecipeIngredient> recipeIngredients)
  {
    for(RecipeIngredient ingredientOrder : recipeIngredients)
    {
      boolean found = false;

      for(Ingredient ingredientStock : stock)
      {
        if (ingredientStock.getId().equals(ingredientOrder.getIngredient().getId()) && ingredientStock.getStock() >= ingredientOrder.getAmount())
        {
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

  public boolean hasStockForOrder(Order order)
  {
    return hasStockForRecipeIngredients(getRecipeIngredientsInOrder(order));
  }

  public void removeRecipeIngredientsFromOrder(Order order)
  {
    removeRecipeIngredients(getRecipeIngredientsInOrder(order));
  }

  public void addRecipeIngredientsFromOrder(Order order)
  {
    addRecipeIngredients(getRecipeIngredientsInOrder(order));
  }

  public ArrayList<RecipeIngredient> getRecipeIngredientsInOrder(Order order)
  {
    ArrayList<RecipeIngredient> ingredientsInOrder = new ArrayList<>();

    for (OrderItem orderItem : order.getOrderItems())
    {
      System.out.println(orderItem.getQuantity());
      int amount = orderItem.getQuantity();

      for (Recipe recipe : orderItem.getItem().getRecipes())
      {
        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients())
        {
          addRecipeIngredientToList(ingredientsInOrder,recipeIngredient,amount);
        }
      }
    }

    return ingredientsInOrder;
  }

  public ArrayList<RecipeIngredient> getRecipeIngredientsInMenuItem(MenuItem menuItem)
  {
    ArrayList<RecipeIngredient> ingredientsInOrder = new ArrayList<>();

    for (Recipe recipe : menuItem.getRecipes())
    {
      for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients())
      {
        addRecipeIngredientToList(ingredientsInOrder,recipeIngredient,1);
      }
    }
    return ingredientsInOrder;
  }

  public double getStockForIngredient(Ingredient ingredient)
  {
    for (Ingredient ingredientStock : stock)
    {
      if (ingredientStock.getId().equals(ingredient.getId()))
      {
        return ingredientStock.getStock();
      }
    }
    return 0;
  }

  public int amountOfStockForMenuItem(MenuItem menuItem)
  {
    int minStock = Integer.MAX_VALUE;

    for (RecipeIngredient recipeIngredient : getRecipeIngredientsInMenuItem(menuItem))
    {
      if (recipeIngredient.getAmount() > 0)
      {
        int possible = (int) (getStockForIngredient(recipeIngredient.getIngredient()) / recipeIngredient.getAmount());
        minStock = Math.min(minStock, possible);
      }
    }
    return minStock == Integer.MAX_VALUE ? 0 : minStock;
  }

  private void addRecipeIngredientToList(ArrayList<RecipeIngredient> ingredientList, RecipeIngredient recipeIngredient, int amount)
  {
    RecipeIngredient addedIngredient = recipeIngredient;
    for (RecipeIngredient ingredientListElement : ingredientList)
    {
      if (ingredientListElement.getIngredient().getId().equals(addedIngredient.getIngredient().getId()))
      {
        ingredientListElement.addAmount(recipeIngredient.getAmount()*amount);
        return;
      }
    }
    ingredientList.add(new RecipeIngredient(recipeIngredient.getIngredient(),recipeIngredient.getAmount()*amount));
  }

  public void removeRecipeIngredients(ArrayList<RecipeIngredient> recipeIngredientsInOrder)
  {
    for (Ingredient ingredient : stock)
    {
      for (RecipeIngredient recipeIngredientInOrder : recipeIngredientsInOrder)
      {
        if (ingredient.getId().equals(recipeIngredientInOrder.getIngredient().getId()))
        {
          double amount = ingredient.getStock()-recipeIngredientInOrder.getAmount();
          menuDAO.setAmountOnIngredient(ingredient.getId(),recipeIngredientInOrder.getAmount());
          ingredient.setStock(amount);
        }
      }
    }
  }

  public void addRecipeIngredients(ArrayList<RecipeIngredient> ingredientsInOrder)
  {
    for (Ingredient ingredient : stock)
    {
      for (RecipeIngredient recipeIngredientInOrder : ingredientsInOrder)
      {
        if (ingredient.getId().equals(recipeIngredientInOrder.getIngredient().getId()))
        {
          double amount = ingredient.getStock()+recipeIngredientInOrder.getAmount();
          menuDAO.setAmountOnIngredient(ingredient.getId(),-recipeIngredientInOrder.getAmount());
          ingredient.setStock(amount);
        }
      }
    }
  }
}
