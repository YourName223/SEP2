package model;

import java.util.ArrayList;

public class OrderManager
{
  private ArrayList<Order> orders;

  public OrderManager()
  {
    orders = new ArrayList<>();
  }

  public TableOrder createTableOrder(Order order, String tableNr)
  {
    return new TableOrder(order,tableNr);
  }

  public void addOrder(Order order)
  {
    orders.add(order);
    new OrderPrinter().printOrder(order);
  }
}
