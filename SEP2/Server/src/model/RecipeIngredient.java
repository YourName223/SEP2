package model;

public class RecipeIngredient
{
  private Ingredient ingredient;
  private int amount;

  public RecipeIngredient(Ingredient ingredient, int amount)
  {
    this.ingredient = ingredient;
    this.amount = amount;
  }

  public Ingredient getIngredient()
  {
    return ingredient;
  }

  public int getAmount()
  {
    return amount;
  }
}