package model;

public class TableOrder extends Order
{
  private String tableNr;

  public TableOrder(Order order, String tableNr)
  {
    super(order.getContent());
    super.setOrderType("Table");
    super.setMenuItems(order.getItems());
    this.tableNr = tableNr;
  }

  public String getTableNr()
  {
    return tableNr;
  };

  public String toString()
  {
    StringBuilder string = new StringBuilder();

    string.append("TableNr:").append(tableNr);

    string.append(super.toString());

    return string.toString();
  }
}
