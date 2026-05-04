package model;

import java.util.ArrayList;
import java.util.List;

public class Recipe
{
  private String id;
  private String name;
  private List<RecipeIngredient> ingredients;

  public Recipe(String id, String name)
  {
    this.id = id;
    this.name = name;
    this.ingredients = new ArrayList<>();
  }

  public String getName()
  {
    return name;
  }

  public void addIngredient(Ingredient ingredient, int amount)
  {
    ingredients.add(new RecipeIngredient(ingredient, amount));
  }

  public List<RecipeIngredient> getIngredients()
  {
    return ingredients;
  }

  public String getId()
  {
    return id;
  }
}