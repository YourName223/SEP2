package model;

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
}
