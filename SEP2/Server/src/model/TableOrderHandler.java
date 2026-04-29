package model;

public class TableOrderHandler implements OrderHandler
{
  private TableManager tableManager;
  private OrderManager orderManager;
  private String type;

  public TableOrderHandler(OrderManager orderManager)
  {
    tableManager = new TableManager();
    this.orderManager = orderManager;
    type = "Table";
  }

  @Override public void handle(Order order)
  {
    Table table = tableManager.secureTable(((TableOrder)order).getTableNr());
    orderManager.addOrder(order);
    table.assignOrder(order);
  }

  @Override public String getType()
  {
    return type;
  }
}
