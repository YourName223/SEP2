package model;

import java.util.ArrayList;

public class Composite implements Component
{
  ArrayList<Component> children;

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
}
