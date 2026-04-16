package model;

public class OrderManager
{
  private TableList tableList;

  public OrderManager(TableList tableList)
  {
    this.tableList = tableList;
  }

  public void addOrder(String tableNr, Order order)
  {
    tableList.addOrder(tableNr,order);
  }
}
