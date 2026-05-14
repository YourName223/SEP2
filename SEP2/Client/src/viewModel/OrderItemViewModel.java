package viewModel;

import javafx.beans.property.StringProperty;
import model.OrderItem;

public class OrderItemViewModel
{
  private OrderItem orderItem;
  private StringProperty timer;

  public OrderItemViewModel(OrderItem orderItem)
  {
    this.orderItem = orderItem;
    //timer = new SimpleStringProperty(getOrderItem().getMenuItem().getTime());
  }

  public OrderItem getOrderItem()
  {
    return orderItem;
  }

  public String getName()
  {
    return orderItem.getMenuItem().getName();
  }

  public double getPrice()
  {
    return orderItem.getMenuItem().getPrice();
  }

  public int getQuantity()
  {
    return orderItem.getQuantity();
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
