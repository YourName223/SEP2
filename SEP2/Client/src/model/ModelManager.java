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
    client = new Client(this,"10.154.208.67",2910);
    getMenuFromDataBase();
  }

  @Override public void createOrder()
  {
    orderManager.createOrder();
  }

  @Override public void addToOrder(MenuItemDto menuItem, int amount)
  {
    orderManager.addToOrder(menuItem, amount);
  }

  @Override public void updateOrderItem(MenuItemDto menuItem, int amount)
  {
    orderManager.updateOrderItem(menuItem,amount);
  }

  @Override public void removeFromOrder(MenuItemDto menuItem)
  {
    orderManager.removeFromOrder(menuItem);
  }

  @Override public void removeOrder()
  {
    orderManager.removeOrder();
  }

  @Override public void removeAllOrders()
  {
    orderManager.removeAllOrders();
  }

  @Override public void placeOrder()
  {
    if(orderManager.getOrder().getItems().isEmpty())
    {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    else
    {
      client.sendOrder(new OrderPackage("Order",orderManager.getOrderItemDto(),"Get"));
    }
  }

  @Override public void orderFeedback(String message)
  {
    if(message.equals("Order accepted"))
    {
      orderManager.acceptOrder();
    }
    property.firePropertyChange("Update",null,message);
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

  @Override public ArrayList<MenuItemDto> getMenu()
  {
    return menu;
  }

  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(propertyName,listener);
  }

  @Override public Order getOrder()
  {
    return orderManager.getOrder();
  }
}
