package viewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuListViewModel implements PropertyChangeListener
{
  private Model model;
  private MenuItemDto selectedMenuItem;
  private final ObservableList<MenuViewModel> menuItems = FXCollections.observableArrayList();
  private IntegerProperty amount;

  public MenuListViewModel(Model model)
  {
    this.model = model;
    selectedMenuItem = null;
    amount = new SimpleIntegerProperty();
    loadFromModel();
  }

  public IntegerProperty getAmount()
  {
    return amount;
  }

  public void increase()
  {
    amount.set(amount.get()+1);
  }

  public void decrease()
  {
    if(amount.get() > 0)
      amount.set(amount.get()-1);
  }

  public void selectMenuItem(MenuItemDto menuItem)
  {
    this.selectedMenuItem = menuItem;
    amount.set(0);
  }

  public void addToOrder()
  {
    if(amount.get() > 0)
    {
      model.addToOrder(selectedMenuItem,amount.get());
    }
  }

  public ObservableList<MenuViewModel> getMenuItems()
  {
    return menuItems;
  }

  public void loadFromModel()
  {
    menuItems.clear();

    for(MenuItemDto menuItem : model.getMenu())
    {
      menuItems.add(new MenuViewModel(model,menuItem));
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    loadFromModel();
  }
}
