package model;

public class RecipeIngredient
{
  private Ingredient ingredient;
  private double amount;

  public RecipeIngredient(Ingredient ingredient, double amount)
  {
    this.ingredient = ingredient;
    this.amount = amount;
  }

  public Ingredient getIngredient()
  {
    return ingredient;
  }

  public double getAmount()
  {
    return amount;
  }
}