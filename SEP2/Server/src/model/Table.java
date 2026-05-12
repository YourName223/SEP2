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
  }

  public String getTableNr()
  {
    return tableNr;
  }

  public ArrayList<Order> getOrders()
  {
    return totalOrder;
  }

  public void removeOrder(Order order)
  {
    totalOrder.remove(order);
  }

  public void removeAllOrders()
  {
    totalOrder.clear();
  }

  public Table copy()
  {
    Table table = new Table(tableNr);

    for(Order order : totalOrder)
    {
      table.assignOrder(order);
    }

    return table;
  }

  public double getTotalPrice()
  {
    double price = 0;
    for (Order order : totalOrder)
    {
      price += order.getTotalPrice();
    }
    return price;
  }
}
