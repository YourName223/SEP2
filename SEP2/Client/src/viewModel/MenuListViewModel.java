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
  private IntegerProperty amountProperty;
  private StringProperty errorProperty;

  public MenuListViewModel(Model model)
  {
    this.model = model;
    selectedMenuItem = null;
    amountProperty = new SimpleIntegerProperty();
    this.errorProperty = new SimpleStringProperty();

    model.addListener("Update", this);
    loadFromModel();
  }

  public IntegerProperty getAmountProperty()
  {
    return amountProperty;
  }

  public void increase()
  {
    int quantity = 0;
    for (OrderItem orderItem : model.getOrder().getItems())
    {
      if (orderItem.getMenuItem().getName().equals(selectedMenuItem.getName()))
      {
        quantity = orderItem.getQuantity();
      }
    }
    if(selectedMenuItem.getStock()>amountProperty.get()+quantity)
    {
      amountProperty.set(amountProperty.get() + 1);
    }
  }

  public void decrease()
  {
    if(amountProperty.get() > 0)
      amountProperty.set(amountProperty.get()-1);
  }

  public void selectMenuItem(MenuItemDto menuItem)
  {
    this.selectedMenuItem = menuItem;
    amountProperty.set(0);
  }

  public void addToOrder()
  {
    if(amountProperty.get() > 0)
    {
      try
      {
        errorProperty.set("");
        model.addToOrder(selectedMenuItem, amountProperty.get());
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
