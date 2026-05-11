package viewModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MenuItemDto;
import model.Model;
import model.OrderItem;
import model.Table;

public class TablesViewModel
{
  private Model model;
  private Table selectedTable;
  private final ObservableList<TablesViewModel> tables = FXCollections.observableArrayList();

  public void loadFromModel(Model model)
  {
    this.model = model;
    selectedTable = null;

    //model.addListener("Update",this);
    loadFromModel();
  }

  public void clear()
  {
    loadFromModel();
  }

  public void loadFromModel()
  {
    selectedTable = null;
  }

  public ObservableList<TablesViewModel> getTables()
  {
    return tables;
  }

  public void showOrders()
  {
  }

  public void setSelectedTable(Table table)
  {
    this.selectedTable = table;
    //total.set(table.getTotal());
  }
}
