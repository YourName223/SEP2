package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private PropertyChangeSupport property;
  private OrderManager orderManager;
  private OrderSender orderSender;

  public ModelManager()
  {
    orderManager = new OrderManager();
    orderSender = new OrderSender();
    property = new PropertyChangeSupport(this);
  }

  @Override public void createOrder()
  {
    orderManager.createOrder();
  }

  @Override public void placeOrder(Order order)
  {
    orderSender.placeOrder(order);
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
