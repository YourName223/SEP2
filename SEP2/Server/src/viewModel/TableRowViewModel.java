package viewModel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableRowViewModel
{
  private final StringProperty tableNr = new SimpleStringProperty();
  private final DoubleProperty total = new SimpleDoubleProperty();

  public TableRowViewModel(String tableNr, double total)
  {
    this.tableNr.set(tableNr);
    this.total.set(total);
  }

  public String getTableNr()
  {
    return tableNr.get();
  }

  public StringProperty getTableNrProperty()
  {
    return tableNr;
  }

  public DoubleProperty getTotalProperty()
  {
    return total;
  }
}
