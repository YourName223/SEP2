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
  private ArrayList<MenuItem> menu;

  public ModelManager()
  {
    orderManager = new OrderManager();
    property = new PropertyChangeSupport(this);
    orderManager.createOrder();
    client = new Client(this,"10.154.208.74",2910);
    getMenuFromDataBase();
  }

  @Override public void createOrder()
  {
    orderManager.createOrder();
  }

  @Override public void addProductToOrder(MenuItem menuItem, int amount)
  {
    orderManager.addProductToOrder(menuItem, amount);
  }

  @Override public void setProductToOrder(MenuItem menuItem, int amount)
  {
    orderManager.addProductToOrder(menuItem, amount);
  }

  @Override public void removeProductFromOrder(MenuItem menuItem)
  {
    orderManager.removeProductFromOrder(menuItem);
  }

  @Override public void placeOrder()
  {
    if(orderManager.getOrder().getItems().isEmpty())
    {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    else
    {
      client.sendOrder(new OrderPackage("order",orderManager.getOrder(),"Get"));
    }
  }

  @Override public void fireProperty(String propertyName, String line)
  {
    property.firePropertyChange(propertyName,null,line);
  }

  @Override public void changeMenu(ArrayList<MenuItem> menu)
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
    return menu;
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
