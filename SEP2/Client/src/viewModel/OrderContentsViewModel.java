package viewModel;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
  private IntegerProperty amount;


  public OrderContentsViewModel(Model model)
  {
    amount = new SimpleIntegerProperty();
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
    orderItems.clear();
    successProperty.set("");
    errorProperty.set("");
    amount.set(0);
    for(OrderItem item : model.getOrder().getItems())
    {
      orderItems.add(new OrderItemViewModel(model,item));
    }
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
    amount.set(amount.get()+1);
  }

  public void decrease()
  {
    if(amount.get() > 0)
      amount.set(amount.get()-1);
  }

  public IntegerProperty getAmount()
  {
    return amount;
  }

  public void updateQuantity()
  {
    for(OrderItemViewModel orderItem1 : orderItems)
    {
      if (orderItem1.getOrderItem().equals(orderItem))
      {
        orderItem1.getOrderItem().setQuantity(amount.get());
      }
    }
  }

  public void deleteMenuItem()
  {
    for(OrderItemViewModel orderItem1 : orderItems)
    {
      if (orderItem1.getOrderItem().equals(orderItem))
      {
        orderItems.remove(orderItem1);
        break;
      }
    }
  }

  public void setSelectedOrderItem(OrderItem orderItem)
  {
    this.orderItem = orderItem;
    amount.set(orderItem.getQuantity());
    System.out.println(amount.get());
  }
}
