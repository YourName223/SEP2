package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private PropertyChangeSupport property;
  private OrderManager orderManager;
  private OrderSender orderSender;
  private Order order;

  public ModelManager()
  {
    order = null;
    orderManager = new OrderManager();
    orderSender = new OrderSender(this);
    property = new PropertyChangeSupport(this);
  }

  @Override public void createOrder()
  {
    order = orderManager.createOrder();
  }

  @Override public void placeOrder()
  {
    orderSender.placeOrder(order);
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
