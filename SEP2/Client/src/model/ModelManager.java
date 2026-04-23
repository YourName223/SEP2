package model;

import mediator.OrderPackage;
import mediator.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private PropertyChangeSupport property;
  private OrderManager orderManager;
  private Order order;
  private Client client;

  public ModelManager()
  {
    order = null;
    orderManager = new OrderManager();
    property = new PropertyChangeSupport(this);
    client = new Client(this,"10.154.208.86",2910);
  }

  @Override public void createOrder()
  {
    order = orderManager.createOrder();
  }

  @Override public void placeOrder()
  {
    if(order == null)
    {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    else
    {
      client.sendOrder(new OrderPackage(order));
    }
  }

  @Override public void fireProperty(String line)
  {
    property.firePropertyChange("Update",null,line);
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
}
