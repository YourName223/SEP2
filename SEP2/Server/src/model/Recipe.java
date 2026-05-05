package model;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Component
{
  private String id;
  private String name;
  private List<RecipeIngredient> ingredients;
  private ArrayList<Component> children;

  public Recipe(String id, String name)
  {
    this.id = id;
    this.name = name;
    this.ingredients = new ArrayList<>();
    this.children = new ArrayList<>();
  }

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

  @Override
  public String getName()
  {
    return name;
  }

  @Override
  public void addIngredient(Ingredient ingredient)
  {
    ingredients.add(new RecipeIngredient(ingredient, 0));
  }

  @Override
  public void removeIngredient(Ingredient ingredient)
  {
    ingredients.removeIf(ri -> ri.getIngredient().getName().equals(ingredient.getName()));
  }

  @Override
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