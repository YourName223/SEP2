package model;

import java.util.ArrayList;
import java.util.List;

public class Recipe
{
  private String id;
  private String name;
  private List<RecipeIngredient> ingredients;
  //private ArrayList<Component> children; maybe useful for later

  public Recipe(String id, String name)
  {
    this.id = id;
    this.name = name;
    this.ingredients = new ArrayList<>();
    //this.children = new ArrayList<>(); maybe useful for later
  }

  /*
  // Component metoder
  @Override
  public void add(Component in)
  {
    children.add(in);
  }

  @Override
  public void remove(Component in)
  {
    children.remove(in);
  }

  @Override
  public Component getChild(int index)
  {
    return children.get(index);
  }

  maybe useful for later */

  public String getName()
  {
    return name;
  }

  public void addIngredient(Ingredient ingredient)
  {
    ingredients.add(new RecipeIngredient(ingredient, 0));
  }

  public void removeIngredient(Ingredient ingredient)
  {
    ingredients.removeIf(ri -> ri.getIngredient().getName().equals(ingredient.getName()));
  }

  public ArrayList<Ingredient> getIngredients()
  {
    ArrayList<Ingredient> result = new ArrayList<>();
    for (RecipeIngredient ri : ingredients)
    {
      result.add(ri.getIngredient());
    }
    return result;
  }

  // Eksisterende metoder
  public void addIngredient(Ingredient ingredient, int amount)
  {
    ingredients.add(new RecipeIngredient(ingredient, amount));
  }

  public List<RecipeIngredient> getRecipeIngredients()
  {
    return ingredients;
  }

  public String getId()
  {
    return id;
  }
}