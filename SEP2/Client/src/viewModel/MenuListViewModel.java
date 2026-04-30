package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MenuItem;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuListViewModel implements PropertyChangeListener
{
  private Model model;
  private MenuItem menuItem;
  private final ObservableList<MenuViewModel> menuItems = FXCollections.observableArrayList();

  public MenuListViewModel(Model model)
  {
    this.model = model;
    menuItem = null;
  }

  public void clear()
  {
    loadFromModel();
  }

  public void increase()
  {
    if(menuItem != null)
      model.addProductToOrder(menuItem);
  }

  public void decrease()
  {
    if(menuItem != null)
      model.removeProductFromOrder(menuItem);
  }

  public void setSelectedMenuItem(MenuItem menuItem)
  {
    this.menuItem = menuItem;
  }

  public ObservableList<MenuViewModel> getMenuItems()
  {
    return menuItems;
  }

  public MenuItem getMenuItem()
  {
    return menuItem;
  }

  public void loadFromModel()
  {
    ObservableList<MenuItem> list = FXCollections.observableArrayList();
    list.addAll(model.getMenu());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}
