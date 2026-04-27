package model;

import java.util.ArrayList;

public class OrderManager
{
  private ArrayList<Order> orders;

  public void addOrder(Order order)
  {
    orders.add(order);
  }
}
