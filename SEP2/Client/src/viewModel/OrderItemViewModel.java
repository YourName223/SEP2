package viewModel;

import model.Model;
import model.OrderItem;
import model.OrderItemDto;

public class OrderItemViewModel
{
  private Model model;
  private OrderItem orderItem;

  public OrderItemViewModel(Model model, OrderItem orderItem)
  {
    this.model = model;
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
