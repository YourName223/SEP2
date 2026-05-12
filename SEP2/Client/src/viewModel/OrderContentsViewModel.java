package viewModel;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Order;
import model.OrderItem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrderContentsViewModel implements PropertyChangeListener
{
  private final Model model;

  private OrderItem selectedOrderItem;

  private final ObservableList<OrderItemRowViewModel> orderItems =
      FXCollections.observableArrayList();

  private final StringProperty successProperty = new SimpleStringProperty();
  private final StringProperty errorProperty = new SimpleStringProperty();
  private final IntegerProperty amountProperty = new SimpleIntegerProperty();

  public OrderContentsViewModel(Model model)
  {
    this.model = model;

    model.addListener("Update", this);
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

    for (Order oldOrder : model.getOldOrders())
    {
      for (OrderItem item : oldOrder.getItems())
      {
        orderItems.add(
            new OrderItemRowViewModel(
                new OrderItemViewModel(item),
                false
            )
        );
      }
    }

    for (OrderItem item : model.getOrder().getItems())
    {
      orderItems.add(
          new OrderItemRowViewModel(
              new OrderItemViewModel(item),
              true
          )
      );
    }
  }

  public ObservableList<OrderItemRowViewModel> getOrderItems()
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

  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    if ("Update".equals(evt.getPropertyName()))
    {
      Platform.runLater(() -> {
        successProperty.set(String.valueOf(evt.getNewValue()));
        reloadOrderTable();
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
  }

  public void increase()
  {
    if(selectedOrderItem.getMenuItem().getStock()< amountProperty.get()+selectedOrderItem.getMenuItem().getStock())
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
}