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
      System.out.println(ingredientOrder.getName());
      boolean found = false;

      for(Ingredient ingredientStock : stock)
      {
        System.out.println(ingredientStock.getName());
        System.out.println(ingredientStock.getStock() + "," + ingredientOrder.getAmount());
        if (ingredientStock.getId().equals(ingredientOrder.getId()) && ingredientStock.getStock() >= ingredientOrder.getAmount())
        {
          System.out.println("Found it");
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

  public ArrayList<Ingredient> getIngredientsInOrder(ArrayList<OrderItem> orderItems)
  {
    ArrayList<Ingredient> ingredientsInOrder = new ArrayList<>();

    for (OrderItem orderItem : orderItems)
    {
      int amount = orderItem.getQuantity();

      for (Recipe recipe : orderItem.getItem().getRecipes())
      {
        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients())
        {
          addIngredientToList(ingredientsInOrder,recipeIngredient,amount);
        }
      }
    }

    return ingredientsInOrder;
  }

  public ArrayList<Ingredient> getIngredientsInMenuItem(MenuItem menuItem)
  {
    ArrayList<Ingredient> ingredientsInOrder = new ArrayList<>();

    for (Recipe recipe : menuItem.getRecipes())
    {
      for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients())
      {
        addIngredientToList(ingredientsInOrder,recipeIngredient,1);
      }
    }
    return ingredientsInOrder;
  }

  public int amountOfStockForIngredients(ArrayList<Ingredient> ingredients)
  {
    int minStock = Integer.MAX_VALUE;

    for (Ingredient ingredient : ingredients)
    {
      if (ingredient.getAmount() > 0)
      {
        int possible = (int) (ingredient.getStock() / ingredient.getAmount());
        minStock = Math.min(minStock, possible);
      }
    }

    return minStock == Integer.MAX_VALUE ? 0 : minStock;
  }

  private void addIngredientToList(ArrayList<Ingredient> ingredientList, RecipeIngredient recipeIngredient, int amount)
  {
    Ingredient addedIngredient = recipeIngredient.getIngredient();
    for (Ingredient ingredient1 : ingredientList)
    {
      if (ingredient1.getId().equals(addedIngredient.getId()))
      {
        double amount1 = ingredient1.getAmount() + addedIngredient.getAmount()*amount;
        System.out.println(amount1);
        ingredient1.setAmount(ingredient1.getAmount() + addedIngredient.getAmount()*amount);
        return;
      }
    }
    Ingredient ingredient = new Ingredient(addedIngredient.getId(),addedIngredient.getName(),addedIngredient.getAmount()*amount,addedIngredient.getStock());
    ingredientList.add(ingredient);
  }

  public void removeIngredients(ArrayList<Ingredient> ingredientsInOrder)
  {
    for (Ingredient ingredient : stock)
    {
      for (Ingredient ingredientInOrder : ingredientsInOrder)
      {
        if (ingredient.getId().equals(ingredientInOrder.getId()))
        {
          double amount = ingredient.getStock()-ingredientInOrder.getAmount();
          menuDAO.setAmountOnIngredient(ingredient.getId(),ingredientInOrder.getAmount());
          ingredient.setStock(amount);
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
          double amount = ingredient.getStock()+ingredientInOrder.getAmount();
          menuDAO.setAmountOnIngredient(ingredient.getId(),-ingredientInOrder.getAmount());
          ingredient.setStock(amount);
        }
      }
    }
  }
}
