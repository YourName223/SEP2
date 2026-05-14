package model;

import java.util.ArrayList;

public class OrderListCurrent
{
  private ArrayList<OrderCurrent> orders;

  public OrderListCurrent()
  {
    orders = new ArrayList<>();
  }

  public void addOrder(Order order)
  {
    orders.add(new OrderCurrent(order));
  }

  public void click(OrderCurrent order)
  {
    order.click();
    if(order.shouldRemove())
    {
      orders.remove(order);
    }
  }

  public void removeOrder(OrderCurrent order)
  {
    orders.remove(order);
  }

  public ArrayList<OrderCurrent> getOrders()
  {
    return orders;
  }

  public void removeOrderItem(Order order, OrderItem orderItem)
  {
    for (OrderCurrent orderCurrent : orders)
    {
      Order order1 = orderCurrent.getOrder();
      if (order1.equals(order))
      {
        order1.removeOrderItem(orderItem);
      }
    }
  }
}
