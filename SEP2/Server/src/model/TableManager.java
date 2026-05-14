package model;

import java.util.ArrayList;

public class TableManager
{
  public TableList tableList;

  public TableManager()
  {
    this.tableList = new TableList();
  }

  public void secureTable(String tableNr)
  {
    if(!tableList.hasTable(tableNr))
    {
      tableList.addTable(tableNr);
    }
  }

  public void assignOrderOnTable(Order order, String tableNr)
  {
    tableList.addOrder(tableNr, order);
  }

  public void removeOrder(Order order)
  {
    if(order.getOrderType().equals("Table"))
    {
      TableOrder tableOrder = (TableOrder)order;
      String tableNr = tableOrder.getTableNr();

      tableList.removeOrder(order,tableNr);
    }
  }

  public void removeAllOrdersFromTable(String tableNr)
  {
    tableList.removeAllOrdersFromTable(tableNr);
  }

  public ArrayList<Order> getOrdersFromTable(String tableNr)
  {
    return tableList.getOrdersFromTable(tableNr);
  }

  public double getPriceFromTable(String tableNr)
  {
    return tableList.getPriceFromTable(tableNr);
  }

  public ArrayList<String> getAllTableNr()
  {
    return tableList.getAllTableNr();
  }

  public void removeOrderItem(Order order, OrderItem orderItem)
  {
    tableList.removeOrderItem(((TableOrder)order).getTableNr() , order, orderItem);
  }
}
