package viewModel;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class OrderContentsViewModel implements PropertyChangeListener
{
  private final Model model;

  private OrderItem selectedOrderItem;

  private final ObservableList<OrderItemViewModel> orderItems =
      FXCollections.observableArrayList();

  private final StringProperty successProperty = new SimpleStringProperty();
  private final StringProperty errorProperty = new SimpleStringProperty();
  private final IntegerProperty amountProperty = new SimpleIntegerProperty();
  private final ObservableList<OrderItemViewModel> oldOrderItems = FXCollections.observableArrayList();

  public OrderContentsViewModel(Model model)
  {
    this.model = model;

    model.addListener("Update", this);
    model.addListener("Time",this);
    loadFromModel();
  }

  public void clear()
  {
    loadFromModel();
  }

  public void loadFromModel()
  {
    successProperty.set("");
    errorProperty.set("");
    amountProperty.set(0);

    reloadOrderTable();
  }

  private void reloadOrderTable()
  {
    orderItems.clear();
    oldOrderItems.clear();

    for (Order oldOrder : model.getOldOrders())
    {
      for (OrderItem item : oldOrder.getItems())
      {
        oldOrderItems.add(new OrderItemViewModel(item));
      }
    }

    for (OrderItem item : model.getOrder().getItems())
    {
      orderItems.add(new OrderItemViewModel(item));
    }
  }

  public ObservableList<OrderItemViewModel> getOrderItems()
  {
    return orderItems;
  }

  public StringProperty getSuccessProperty()
  {
    return successProperty;
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public IntegerProperty getAmountProperty()
  {
    return amountProperty;
  }

  public ObservableList<OrderItemViewModel> getOldOrderItems()
  {
    return oldOrderItems;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals("Update"))
    {
      Platform.runLater(() ->
      {
        successProperty.set(String.valueOf(evt.getNewValue()));
        reloadOrderTable();
      });
    }
    else if(evt.getPropertyName().equals("Time"))
    {
      Platform.runLater(() ->
      {
        ArrayList<OrderItem> orderItemList = (ArrayList<OrderItem>) evt.getOldValue();

        ArrayList<String> time = (ArrayList<String>) evt.getNewValue();

        for (OrderItemViewModel row : orderItems)
        {
          for (int i = 0; i<orderItemList.size();i++)
          {
            if (row.getOrderItem().getMenuItem().getName().equals((orderItemList.get(i).getMenuItem().getName())))
            {
              //row.setTime(time.get(i));
            }
          }
        }
      });
    }
  }

  public void setSelectedOrderItem(OrderItem orderItem)
  {
    this.selectedOrderItem = orderItem;
    if (orderItem != null)
    {
      amountProperty.set(orderItem.getQuantity());
    }
    if (orderItem == null)
    {
      amountProperty.set(0);
    }
  }

  public void increase()
  {
    if(selectedOrderItem.getMenuItem().getStock()>amountProperty.get()+selectedOrderItem.getQuantity())
    {
      amountProperty.set(amountProperty.get() + 1);
    }
  }

  public void decrease()
  {
    if (amountProperty.get() > 0)
      amountProperty.set(amountProperty.get() - 1);
  }

  public void updateQuantity()
  {
    if (selectedOrderItem == null) return;

    if (amountProperty.get() == 0)
    {
      model.removeFromOrder(selectedOrderItem.getMenuItem());
    }
    else
    {
      model.updateOrderItem(selectedOrderItem.getMenuItem(), amountProperty.get());
    }

    reloadOrderTable();
  }

  public void deleteMenuItem()
  {
    if (selectedOrderItem == null) return;

    model.removeFromOrder(selectedOrderItem.getMenuItem());
    reloadOrderTable();
  }

  public void placeOrder()
  {
    model.placeOrder();
  }

  public void cancelOrder()
  {
    if (selectedOrderItem == null) return;

    model.cancelOrder(selectedOrderItem);
    reloadOrderTable();
  }
}