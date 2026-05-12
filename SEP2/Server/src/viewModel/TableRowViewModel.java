package viewModel;

public class TableRowViewModel
{
  private final String tableNr;
  private final double total;

  public TableRowViewModel(String tableNr, double total)
  {
    this.tableNr = tableNr;
    this.total = total;
  }

  public String getTableNr() { return tableNr; }
  public double getTotal() { return total; }
}
