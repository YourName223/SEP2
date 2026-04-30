package viewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MenuItem;
import model.Model;
import model.OrderItem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrderContentsViewModel implements PropertyChangeListener
{
  private Model model;
  private OrderItem orderItem;
  private final ObservableList<OrderItemViewModel> orderItems = FXCollections.observableArrayList();
  private StringProperty successProperty;
  private StringProperty errorProperty;
  private int amount;


  public OrderContentsViewModel(Model model)
  {
    this.model = model;
    orderItem = null;
    this.successProperty = new SimpleStringProperty();
    this.errorProperty = new SimpleStringProperty();

    model.addListener("Update",this);
    loadFromModel();
  }

  public void clear()
  {
    loadFromModel();
  }

  public void loadFromModel()
  {
    System.out.println("Trying to get order test");
    successProperty.set("");
    errorProperty.set("");
    for(OrderItem orderItem : model.getOrder().getItems())
    {
      System.out.println("Trying to get order v2 test");
      orderItems.add(new OrderItemViewModel(model,orderItem));
    }
  }

  public void loadAgain()
  {
    loadFromModel();
  }

  public StringProperty getSuccessProperty()
  {
    return successProperty;
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals("Update"))
    {
      Platform.runLater( () -> successProperty.set(evt.getNewValue().toString()));
    }
  }

  public ObservableList<OrderItemViewModel> getOrderItems()
  {
    return orderItems;
  }

  public void placeOrder()
  {
    model.placeOrder();
  }

  public void increase()
  {
    amount ++;
  }

  public void decrease()
  {
    amount --;
  }

  public void updateQuantity()
  {
    for(OrderItemViewModel orderItem1 : orderItems)
    {
      if (orderItem1.getOrderItem().getItem().equals(orderItem))
      {
        orderItem1.getOrderItem().setQuantity(amount);
      }
    }
  }

  public void deleteMenuItem()
  {
    for(OrderItemViewModel orderItem1 : orderItems)
    {
      if (orderItem1.getOrderItem().getItem().equals(orderItem))
      {
        orderItems.remove(orderItem1);
      }
    }
  }

  public void setSelectedOrderItem(OrderItem orderItem)
  {
    this.orderItem = orderItem;
    boolean orderItemIsInOrder = false;
    for(OrderItemViewModel orderItem1 : orderItems)
    {
      if (orderItem1.getOrderItem().getItem().equals(orderItem))
      {
        orderItemIsInOrder = true;
        break;
      }
    }
    if(orderItemIsInOrder)
      for(OrderItemViewModel orderItem1 : orderItems)
      {
        if (orderItem1.getOrderItem().getItem().equals(orderItem))
        {
          amount = orderItem.getQuantity();
          break;
        }
      }
    else
    {
      amount = 0;
    }
  }
}
