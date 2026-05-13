package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TableOrdersViewModel implements PropertyChangeListener
{
  private final Model model;

  private final StringProperty successProperty = new SimpleStringProperty();
  private final StringProperty errorProperty = new SimpleStringProperty();
  private final DoubleProperty totalProperty = new SimpleDoubleProperty();
  private final StringProperty tableNrProperty = new SimpleStringProperty();

  private String selectedTableNr;

  private final ObservableList<OrderItemRowStatusViewModel> rows =
      FXCollections.observableArrayList();

  public TableOrdersViewModel(Model model)
  {
    this.model = model;

    model.addListener("Update",this);
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

    tableNrProperty.set(selectedTableNr);

    totalProperty.set(model.getPriceFromTable(selectedTableNr));

    for (Order order : model.getOrdersFromTable(selectedTableNr))
    {
      for (OrderItem item : order.getOrderItems())
      {
        rows.add(new OrderItemRowStatusViewModel(
            item.getQuantity(),
            item.getItem().getName(),
            item.getItem().getPrice()
        ));
      }
    }
  }

  public ObservableList<OrderItemRowStatusViewModel> getRows()
  {
    return rows;
  }

  public StringProperty getSuccessProperty() { return successProperty; }
  public StringProperty getErrorProperty() { return errorProperty; }
  public DoubleProperty getTotalProperty() { return totalProperty; }
  public StringProperty getTableNrProperty() { return tableNrProperty; }

  public void resetOrders()
  {
    if (selectedTableNr == null) return;

    model.removeAllOrdersFromTable(selectedTableNr);
    loadFromModel();
  }

  public void clear()
  {
    loadFromModel();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    loadFromModel();
  }
}