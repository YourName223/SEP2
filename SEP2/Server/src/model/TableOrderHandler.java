package model;

public class TableOrderHandler implements OrderHandler
{
  private TableManager tableManager;
  private String type;

  public TableOrderHandler(TableManager tableManager)
  {
    this.tableManager = tableManager;
    type = "Table";
  }

  @Override public void handle(Order order)
  {
    Table table = tableManager.secureTable(((TableOrder)order).getTableNr());
    table.assignOrder(order);
  }

  @Override public String getType()
  {
    return type;
  }
}
