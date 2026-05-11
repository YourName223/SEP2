package viewModel;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.Order;
import model.OrderItem;
import model.Table;

public class TableOrdersViewModel
{
  private Model model;
  private StringProperty successProperty;
  private StringProperty errorProperty;
  private DoubleProperty total;
  private StringProperty tableNumber;

  private final ObservableList<OrderItemRow> rows = FXCollections.observableArrayList();

  public TableOrdersViewModel(Model model)
  {
    total = new SimpleDoubleProperty();
    this.model = model;
    this.successProperty = new SimpleStringProperty();
    this.errorProperty = new SimpleStringProperty();

    //model.addListener("Update",this);
    loadFromModel();
  }

  public void clear()
  {
    loadFromModel();
  }

  public void loadFromModel()
  {
    successProperty.set("");
    errorProperty.set("");

    rows.clear();

    Table table = model.getSelectedTable();

    tableNumber.set(String.valueOf(table.getTableNr()));
    total.set(String.format("%.2f", table.getTotal()));

    for (Order order : table.getOrders())
    {
      for (OrderItem item : order.getOrderItems())
      {
        rows.add(
            new OrderItemRow(item.getQuantity(), item.getItem().getName(),
                item.getItem().getPrice()));
      }
    }
  }

  public ObservableList<OrderItemRow> getRows()
  {
    return rows;
  }

  public StringProperty getSuccessProperty()
  {
    return successProperty;
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public ObservableValue<String> getTotal()
  {
return total;
  }

  public ObservableValue<String> getTableNumber()
  {
    return tableNumber;
  }

  public void backToTables()
  {
  }

  public void resetOrders()
  {
    model.resetOrdersForSelectedTable();
    loadFromModel();
  }
}
