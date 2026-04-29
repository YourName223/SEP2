package model;

public class Product implements Component
{
  private String name;

  public Product(String name)
  {
    this.name = name;
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
}
