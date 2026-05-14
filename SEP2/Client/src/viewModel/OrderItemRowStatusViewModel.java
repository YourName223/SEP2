package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.OrderItem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrderItemRowStatusViewModel
{
  private final OrderItemViewModel itemVM;
  private final boolean selectable;
  private StringProperty timer;

  public OrderItemRowStatusViewModel(OrderItemViewModel itemVM, boolean selectable)
  {
    this.itemVM = itemVM;
    this.selectable = selectable;
    timer = new SimpleStringProperty(getOrderItem().getMenuItem().getTime());
  }

  public boolean isSelectable()
  {
    return selectable;
  }

  public String getName()
  {
    return itemVM.getName();
  }

  public double getPrice()
  {
    return itemVM.getPrice();
  }

  public int getQuantity()
  {
    return itemVM.getQuantity();
  }

  public OrderItem getOrderItem()
  {
    return itemVM.getOrderItem();
  }

  public void setTimer(String timeText)
  {
    this.timer.set(timeText);
  }

  public StringProperty timeProperty()
  {
    return timer;
  }
}