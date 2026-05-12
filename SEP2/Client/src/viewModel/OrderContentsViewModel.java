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
  private final IntegerProperty amount = new SimpleIntegerProperty();

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
    amount.set(0);

    reloadOrderTable();
  }

  private void reloadOrderTable()
  {
    orderItems.clear();

    for (OrderItem item : model.getOrder().getItems())
    {
      orderItems.add(
          new OrderItemRowViewModel(
              new OrderItemViewModel(item),
              true
          )
      );
    }

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

  public IntegerProperty getAmount()
  {
    return amount;
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
      amount.set(orderItem.getQuantity());
    }
  }

  public void increase()
  {
    amount.set(amount.get() + 1);
  }

  public void decrease()
  {
    if (amount.get() > 0)
      amount.set(amount.get() - 1);
  }

  public void updateQuantity()
  {
    if (selectedOrderItem == null) return;

    if (amount.get() == 0)
    {
      model.removeFromOrder(selectedOrderItem.getMenuItem());
    }
    else
    {
      model.updateOrderItem(selectedOrderItem.getMenuItem(), amount.get());
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