package model;

import java.util.ArrayList;

public class Composite implements Component
{
  private String name;
  ArrayList<Component> children;

  public Composite(String name)
  {
    this.name = name;
    children = new ArrayList<>();
  }

  @Override public void add(Component in)
  {
    children.add(in);
  }

  @Override public void remove(Component in)
  {
    children.remove(in);
  }

  @Override public Component getChild(int index)
  {
    return children.get(index);
  }

  @Override public String getName()
  {
    StringBuilder name = new StringBuilder("{");

    name.append(this.name);

    for (Component component : children)
    {
      name.append("{").append(component.getName()).append("},");
    }

    if (name.length() > 1)
      name.setLength(name.length() - 1);

    name.append("}");

    String result = name.toString();
    return result;
  }

  @Override
  public ArrayList<Ingredient> getIngredients()
  {
    ArrayList<Ingredient> result = new ArrayList<>();

    for (Component child : children)
    {
      result.addAll(child.getIngredients());
    }

    return result;
  }

  @Override public void addIngredient(Ingredient ingredient)
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public void removeIngredient(Ingredient ingredient)
  {
    throw new UnsupportedOperationException();
  }
}
