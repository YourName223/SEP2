package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MenuItem;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuViewModel
{
  private Model model;
  private MenuItem menuItem;

  public MenuViewModel(Model model, MenuItem menuItem)
  {
    this.model = model;
    this.menuItem = menuItem;
  }

  public MenuItem getMenuItem()
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
