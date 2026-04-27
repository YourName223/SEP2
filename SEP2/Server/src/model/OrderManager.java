package model;

import java.util.ArrayList;

public class OrderManager
{
  private ArrayList<Order> orders;

  public OrderManager()
  {
    orders = new ArrayList<>();
  }

  public void addOrder(Order order)
  {
    orders.add(order);
  }
}
