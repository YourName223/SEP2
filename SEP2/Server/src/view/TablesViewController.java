package view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import viewModel.TablesViewModel;

public class TablesViewController
{
  @FXML private TableView<TablesViewModel> tablesTable;
  @FXML private TableColumn<TablesViewModel, String> tableNumberColumn;
  @FXML private TableColumn<TablesViewModel, Double> totalColumn;

  private ViewHandler viewHandler;
  private TablesViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, TablesViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    tableNumberColumn.setCellValueFactory(
        cell -> new SimpleStringProperty(cell.getValue().getTableNumber()));
    totalColumn.setCellValueFactory(
        cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getTotal()));

    tablesTable.setItems(viewModel.getTables());

    viewModel.loadFromModel();
  }

  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void onTableSelected()
  {
    TablesViewModel selected = tablesTable.getSelectionModel().getSelectedItem();
    if (selected != null)
    {
      viewModel.setSelectedTable(selected.getTable());
    }
  }

  public void showOrdersButton()
  {
    viewModel.showOrders();
  }
}
