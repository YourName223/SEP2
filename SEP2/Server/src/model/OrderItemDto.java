package model;

public class OrderItemDto
{
  private String recipeId;
  private int quantity;

  public OrderItemDto(String recipeId, int quantity)
  {
    this.recipeId = recipeId;
    this.quantity = quantity;
  }

  public String getRecipeId()
  {
    return recipeId;
  }

  public int getQuantity()
  {
    return quantity;
  }
}