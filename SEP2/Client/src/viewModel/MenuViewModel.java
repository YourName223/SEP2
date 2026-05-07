package viewModel;

import javafx.beans.property.*;
import model.MenuItemDto;

public class MenuViewModel
{
  private MenuItemDto menuItem;

  public MenuViewModel(MenuItemDto menuItem)
  {
    this.menuItem = menuItem;
  }

  public MenuItemDto getMenuItem()
  {
    return menuItem;
  }

  public String getName()
  {
    return menuItem.getName();
  }

  public String getAllergies()
  {
    return menuItem.getAllergies();
  }

  public double getPrice()
  {
    return menuItem.getPrice();
  }
}
