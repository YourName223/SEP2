package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private PropertyChangeSupport property;
  private OrderManager orderManager;

  public ModelManager()
  {
    orderManager = new OrderManager();
    property = new PropertyChangeSupport(this);
  }

  @Override public void createOrder()
  {
    orderManager.createOrder();
  }

  @Override public void placeOrder(Order order)
  {
    if(order == null)
    {
      throw new IllegalArgumentException("Arguments cannot be null");
    }

    if(false)//Sends order to server side
    {

    }
    else
    {
      throw new IllegalStateException("Order could not be placed");
    }
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
