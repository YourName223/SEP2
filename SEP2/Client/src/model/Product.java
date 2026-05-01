package model;

import java.util.ArrayList;

public class Product implements Component
{
  private String name;
  private ArrayList<Ingredient> ingredients;

  public Product(String name)
  {
    this.name = name;
    ingredients = new ArrayList<>();
  }


  @Override public void add(Component in)
  {

  }

  @Override public void remove(Component in)
  {

  }

  @Override public Component getChild(int index)
  {
    return null;
  }

  @Override public String getName()
  {
    return name;
  }

  @Override public void addIngredient(Ingredient ingredient)
  {
    ingredients.add(ingredient);
  }

  @Override public void removeIngredient(Ingredient ingredient)
  {
    ingredients.remove(ingredient);
  }

  @Override public ArrayList<Ingredient> getIngredients()
  {
    return ingredients;
  }

}
