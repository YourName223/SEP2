package viewModel;

import model.OrderItem;

public class OrderItemViewModel
{
  private OrderItem orderItem;

  public OrderItemViewModel(OrderItem orderItem)
  {
    this.orderItem = orderItem;
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
}
