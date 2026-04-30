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
  private int amount;

  public MenuListViewModel(Model model)
  {
    this.model = model;
    menuItem = null;
    amount = 0;
  }

  public void clear()
  {
    loadFromModel();
  }

  public void increase()
  {
    amount ++;
  }

  public void decrease()
  {
    amount --;
  }

  public void setSelectedMenuItem(MenuItem menuItem)
  {
    this.menuItem = menuItem;
    amount = 0;
  }

  public void addToOrder()
  {
    for(int i = 0; i < amount; i++)
    {
      model.addProductToOrder(menuItem);
    }
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
    for(MenuItem menuItem : model.getMenu())
    {
      menuItems.add(new MenuViewModel(model,menuItem));
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}
