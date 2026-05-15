package viewModel;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
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
  private final ObservableList<OrderItemViewModel> oldOrderItems =
      FXCollections.observableArrayList();

  private final StringProperty successProperty = new SimpleStringProperty();
  private final StringProperty errorProperty = new SimpleStringProperty();
  private final IntegerProperty amountProperty = new SimpleIntegerProperty();

  private Timeline timeline;
  private boolean timerRunning = false;

  public OrderContentsViewModel(Model model)
  {
    this.model = model;

    model.addListener("Update", this);
    model.addListener("TimeStart", this);
    model.addListener("TimeStop", this);

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

  public ObservableList<OrderItemViewModel> getOldOrderItems()
  {
    return oldOrderItems;
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
    if (evt.getPropertyName().equals("Update"))
    {
      Platform.runLater(() ->
      {
        successProperty.set(String.valueOf(evt.getNewValue()));
        reloadOrderTable();
      });
    }
    else if (evt.getPropertyName().equals("TimeStart"))
    {
      Platform.runLater(() ->
      {
        OrderItem target = (OrderItem) evt.getNewValue();

        for (OrderItemViewModel vm : oldOrderItems)
        {
          if (vm.getOrderItem().equals(target))
          {
            vm.start();
          }
        }

        startTimer();
      });
    }

    else if (evt.getPropertyName().equals("TimeStop"))
    {
      Platform.runLater(() ->
      {
        OrderItem target = (OrderItem) evt.getNewValue();

        for (OrderItemViewModel vm : oldOrderItems)
        {
          if (vm.getOrderItem().equals(target))
          {
            vm.forceZero();
          }
        }
      });
    }
  }

  private void startTimer()
  {
    if (timerRunning) return;

    timeline = new Timeline(
        new KeyFrame(Duration.seconds(1), e -> tickOldOrders())
    );

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

    timerRunning = true;
  }

  private void tickOldOrders()
  {
    for (OrderItemViewModel row : oldOrderItems)
    {
      row.tick();
    }
  }

  public void setSelectedOrderItem(OrderItem orderItem)
  {
    this.selectedOrderItem = orderItem;
    if (orderItem != null)
    {
      amountProperty.set(orderItem.getQuantity());
    }
    else
    {
      amountProperty.set(0);
    }
  }

  public void increase()
  {
    if (selectedOrderItem == null) return;

    if (selectedOrderItem.getMenuItem().getStock() >
        amountProperty.get() + selectedOrderItem.getQuantity())
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