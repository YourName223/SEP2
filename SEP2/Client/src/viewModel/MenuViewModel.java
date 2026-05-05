package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MenuItemDto;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuViewModel
{
  private Model model;
  private MenuItemDto menuItem;

  public MenuViewModel(Model model, MenuItemDto menuItem)
  {
    this.model = model;
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
