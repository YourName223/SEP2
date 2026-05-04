package mediator;

import model.MenuItem;
import model.MenuItemDto;

import java.util.ArrayList;

public class MenuPackage extends BasePackage
{
  private ArrayList<MenuItemDto> menuItems;

  public MenuPackage(String type, ArrayList<MenuItemDto> menuItems)
  {
    super(type);
    this.menuItems = menuItems;
  }

  public ArrayList<MenuItemDto> getMenuItems()
  {
    return menuItems;
  }
}
