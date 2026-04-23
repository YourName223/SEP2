package model;

import java.util.ArrayList;

public class Table
{
  private String tableNr;
  private ArrayList<Order> totalOrder;

  public Table(String tableNr)
  {
    this.tableNr = tableNr;
    totalOrder = new ArrayList<>();
  }

  public void assignOrder(Order order)
  {
    totalOrder.add(order);
    new OrderPrinter().printOrder(order);
  }

  public String getTableNr()
  {
    return tableNr;
  }

  public ArrayList<Order> getOrders()
  {
    return totalOrder;
  }
}
