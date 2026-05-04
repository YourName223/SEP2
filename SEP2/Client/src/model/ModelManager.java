package model;

import mediator.OrderPackage;
import mediator.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private PropertyChangeSupport property;
  private OrderManager orderManager;
  private Client client;
  private ArrayList<MenuItemDto> menu;

  public ModelManager()
  {
    orderManager = new OrderManager();
    property = new PropertyChangeSupport(this);
    orderManager.createOrder();
    client = new Client(this,"10.154.220.81",2910);
    getMenuFromDataBase();
  }

  @Override public void createOrder()
  {
    orderManager.createOrder();
  }

  @Override public void addMenuItemToOrder(MenuItem menuItem, int amount)
  {
    orderManager.addMenuItemToOrder(menuItem, amount);
  }

  @Override public void setMenuItemOnOrder(MenuItem menuItem, int amount)
  {
    orderManager.setMenuItomOnOrder(menuItem, amount);
  }

  @Override public void removeMenuItemFromOrder(MenuItem menuItem)
  {
    orderManager.removeMenuItemFromOrder(menuItem);
  }

  @Override public void placeOrder()
  {
    if(orderManager.getOrder().getItems().isEmpty())
    {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    else
    {
      client.sendOrder(new OrderPackage("order",orderManager.getOrderItemDto(),"Get"));
    }
  }

  @Override public void fireProperty(String propertyName, String line)
  {
    property.firePropertyChange(propertyName,null,line);
  }

  @Override public void changeMenu(ArrayList<MenuItemDto> menu)
  {
    this.menu = menu;
    property.firePropertyChange("Menu",null,menu);
  }

  @Override public void getMenuFromDataBase()
  {
    client.getMenu();
  }

  @Override public ArrayList<MenuItem> getMenu()
  {
    ArrayList<MenuItem> menuItems = new ArrayList<>();

    for (MenuItemDto menuItemDto : menu)
    {
      MenuItem menuItem = new MenuItem(menuItemDto.getName(),menuItemDto.getAllergies(),menuItemDto.getPrice());
      menuItems.add(menuItem);
    }

    return menuItems;
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(propertyName,listener);
  }

  @Override public Order getOrder()
  {
    return orderManager.getOrder();
  }
}
