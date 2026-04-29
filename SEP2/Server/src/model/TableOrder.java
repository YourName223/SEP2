package model;

public class TableOrder extends Order
{
  private String tableNr;

  public TableOrder(Order order, String tableNr)
  {
    super(order.getContent());
    super.setOrderType("Table");
    super.setProducts(order.getItems());
    this.tableNr = tableNr;
  }

  public String getTableNr()
  {
    return tableNr;
  };
}
