package viewModel;

import model.MenuItem;
import model.Model;
import model.OrderItem;

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
    return orderItem.getItem().getName();
  }

  public double getPrice()
  {
    return orderItem.getItem().getPrice();
  }

  public double getQuantity()
  {
    return orderItem.getQuantity();
  }
  public void increase()
  {
    orderItem.add();
  }

  public void decrease()
  {
    orderItem.remove();
  }


  public void remove()
  {
    if(orderItem.getItem() != null)
      model.removeProductFromOrder(orderItem.getItem());
  }
}
