package viewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
  private StringProperty errorProperty;

  public MenuListViewModel(Model model)
  {
    this.model = model;
    selectedMenuItem = null;
    amount = new SimpleIntegerProperty();
    this.errorProperty = new SimpleStringProperty();

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
      try
      {
        errorProperty.set("");
        model.addToOrder(selectedMenuItem, amount.get());
      }
      catch (IllegalStateException e)
      {
        errorProperty.set(e.getMessage());
      }
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
      menuItems.add(new MenuViewModel(menuItem));
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    loadFromModel();
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }
}
