package model;

public class ModelManager implements Model
{
  private TableList tableList;
  private TableManager tableManager;
  private OrderManager orderManager;

  public ModelManager()
  {
    tableList = new TableList();
    tableManager = new TableManager(tableList);
    orderManager = new OrderManager(tableList);
  }

  @Override public void recieveOrder(String tableNr, Order order)
  {
    tableManager.secureTable(tableNr);
    orderManager.addOrder(tableNr,order);
  }
}
