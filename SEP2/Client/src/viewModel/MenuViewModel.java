package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MenuItem;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuViewModel implements PropertyChangeListener
{
  private Model model;
  private MenuItem menuItem;
  private final ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();

  public MenuViewModel(Model model)
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

  public ObservableList<MenuItem> getMenuItems()
  {
    return menuItems;
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
