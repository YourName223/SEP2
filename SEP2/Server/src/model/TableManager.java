package model;

import java.util.ArrayList;

public class TableManager
{
  public TableList tableList;

  public TableManager()
  {
    this.tableList = new TableList();
  }

  public Table secureTable(String tableNr)
  {
    if(tableList.getTable(tableNr) == null)
    {
      tableList.addTable(tableNr);
    }
    return tableList.getTable(tableNr);
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
}
