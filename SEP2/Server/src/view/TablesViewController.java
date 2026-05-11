package view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewModel.TableRow;
import viewModel.TablesViewModel;

public class TablesViewController
{
  @FXML private TableView<TableRow> tablesTable;
  @FXML private TableColumn<TableRow, String> tableNumberColumn;
  @FXML private TableColumn<TableRow, Double> totalColumn;

  private ViewHandler viewHandler;
  private TablesViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, TablesViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    tableNumberColumn.setCellValueFactory(
        cell -> new SimpleStringProperty(cell.getValue().getTableNumber())
    );

    totalColumn.setCellValueFactory(
        cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getTotal())
    );

    tablesTable.setItems(viewModel.getTables());

    viewModel.loadFromModel();
  }

  @FXML
  private void onTableSelected()
  {
    TableRow selected = tablesTable.getSelectionModel().getSelectedItem();

    if (selected != null)
    {
      viewHandler.openTableOrders(selected.getTableNumber());
    }
  }

  public void showOrdersButton()
  {
    TableRow selected = tablesTable.getSelectionModel().getSelectedItem();

    if (selected != null)
    {
      viewHandler.openTableOrders(selected.getTableNumber());
    }
  }

  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }
}