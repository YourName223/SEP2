package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

public class TableOrdersViewModel
{
  private final Model model;

  private final StringProperty successProperty = new SimpleStringProperty();
  private final StringProperty errorProperty = new SimpleStringProperty();
  private final DoubleProperty total = new SimpleDoubleProperty();
  private final StringProperty tableNumber = new SimpleStringProperty();

  private String selectedTableNr;

  private final ObservableList<OrderItemRow> rows =
      FXCollections.observableArrayList();

  public TableOrdersViewModel(Model model)
  {
    this.model = model;
  }

  public void setSelectedTableNr(String tableNr)
  {
    this.selectedTableNr = tableNr;
    loadFromModel();
  }

  public void loadFromModel()
  {
    if (selectedTableNr == null)
    {
      return;
    }

    successProperty.set("");
    errorProperty.set("");

    rows.clear();

    tableNumber.set(selectedTableNr);

    total.set(model.getPriceFromTable(selectedTableNr));

    for (Order order : model.getOrdersFromTable(selectedTableNr))
    {
      for (OrderItem item : order.getOrderItems())
      {
        rows.add(new OrderItemRow(
            item.getQuantity(),
            item.getItem().getName(),
            item.getItem().getPrice()
        ));
      }
    }
  }

  public ObservableList<OrderItemRow> getRows()
  {
    return rows;
  }

  public StringProperty getSuccessProperty() { return successProperty; }
  public StringProperty getErrorProperty() { return errorProperty; }
  public DoubleProperty getTotal() { return total; }
  public StringProperty getTableNumber() { return tableNumber; }

  public void resetOrders()
  {
    if (selectedTableNr == null) return;

    model.removeAllOrdersFromTable(selectedTableNr);
    loadFromModel();
  }

  public void backToTables()
  {
  }
}