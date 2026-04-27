package model;

public class TableOrder extends Order
{
  private String tableNr;

  public TableOrder(String content, String tableNr)
  {
    super(content);
    super.setOrderType("Table");
    this.tableNr = tableNr;
  }

  public String getTableNr;
}
