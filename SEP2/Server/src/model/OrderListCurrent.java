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

  public void click(OrderCurrent order1)
  {
    order1.click();
    if(order1.shouldRemove())
    {
      orders.remove(order1);
    }
  }

  public ArrayList<OrderCurrent> getOrders()
  {
    return orders;
  }
}
