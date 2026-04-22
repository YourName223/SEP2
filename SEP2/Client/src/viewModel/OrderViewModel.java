package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Order;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrderViewModel implements PropertyChangeListener
{
  private Model model;
  private StringProperty successProperty;

  public OrderViewModel(Model model)
  {
    this.model = model;
    this.successProperty = new SimpleStringProperty();
    model.addListener("Update",this);
  }

  public void clear()
  {
    successProperty.set("");
  }

  public StringProperty getSuccessProperty()
  {
    return successProperty;
  }

  public void createOrder()
  {
    model.createOrder();
  }

  public void placeOrder()
  {
    model.placeOrder();
    successProperty.set("Order was placed");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals("Update"))
    {
      //Do some update
    }
  }
}
