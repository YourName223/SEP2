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
    tableManager.secureTable(((TableOrder)order).getTableNr());
    tableManager.assignOrderOnTable(order,((TableOrder) order).getTableNr());
  }

  @Override public String getType()
  {
    return type;
  }
}
