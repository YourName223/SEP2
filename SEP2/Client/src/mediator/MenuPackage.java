package mediator;

import model.MenuItem;

import java.util.ArrayList;

public class MenuPackage extends BasePackage
{
  private ArrayList<MenuItem> menuItems;

  public MenuPackage(String type, ArrayList<MenuItem> menuItems)
  {
    super(type);
    this.type = type;
    this.menuItems = menuItems;
  }

  public ArrayList<MenuItem> getMenuItems()
  {
    return menuItems;
  }
}
