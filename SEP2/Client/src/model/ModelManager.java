package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private PropertyChangeSupport property;

  public ModelManager()
  {
    property = new PropertyChangeSupport(this);
  }

  @Override public boolean placeOrder(Table table, Order order)
  {
    if(table == null || order == null)
    {
      throw new IllegalArgumentException("Arguments cannot be null");
    }

    if(false)//Sends order to server side
    {
      return true;
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
