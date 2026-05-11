package viewModel;

public class TableRow
{
  private final String tableNumber;
  private final double total;

  public TableRow(String tableNumber, double total)
  {
    this.tableNumber = tableNumber;
    this.total = total;
  }

  public String getTableNumber() { return tableNumber; }
  public double getTotal() { return total; }
}
