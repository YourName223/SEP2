package view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewModel.TableRowViewModel;
import viewModel.TablesViewModel;

public class TablesViewController implements ViewController<TablesViewModel>
{
  @FXML private TableView<TableRowViewModel> tablesTable;
  @FXML private TableColumn<TableRowViewModel, String> tableNrColumn;
  @FXML private TableColumn<TableRowViewModel, Double> totalColumn;

  private TablesViewModel viewModel;
  private TabsViewController tabsViewController;

  private TableRowViewModel selectedTable;

  @Override public void init(TablesViewModel viewModel)
  {
    this.viewModel = viewModel;

    tableNrColumn.setCellValueFactory(
        cell -> cell.getValue().getTableNrProperty());

    totalColumn.setCellValueFactory(
        cell -> cell.getValue().getTotalProperty().asObject());

    tablesTable.setItems(viewModel.getTables());
    viewModel.loadFromModel();
  }

  public void setTabsController(TabsViewController tabsViewController)
  {
    this.tabsViewController = tabsViewController;
  }

  @FXML private void onTableSelected()
  {
    selectedTable = tablesTable.getSelectionModel().getSelectedItem();
  }

  @FXML private void showOrdersButton()
  {
    if (selectedTable == null)
    {
      selectedTable = tablesTable.getSelectionModel().getSelectedItem();
    }

    if (selectedTable == null)
    {
      return;
    }

    if (tabsViewController == null)
    {
      return;
    }

    tabsViewController.showTableOrdersView(selectedTable.getTableNr());
  }

  public void reset()
  {
    viewModel.clear();
  }
}