package model;

public interface Component
{
  public void add(Component in);
  public void remove(Component in);
  public Component getChild(int index);
}
