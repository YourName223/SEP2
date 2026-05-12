package view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewModel.TableRow;
import viewModel.TablesViewModel;

public class TablesViewController
    implements ViewController<TablesViewModel>
{
  @FXML private TableView<TableRow> tablesTable;
  @FXML private TableColumn<TableRow, String> tableNumberColumn;
  @FXML private TableColumn<TableRow, Double> totalColumn;

  private TablesViewModel viewModel;
  private TabsViewController tabsViewController;

  @Override
  public void init(TablesViewModel viewModel)
  {
    this.viewModel = viewModel;

    tableNumberColumn.setCellValueFactory(
        cell -> new SimpleStringProperty(
            cell.getValue().getTableNumber()
        )
    );

    totalColumn.setCellValueFactory(
        cell -> new ReadOnlyObjectWrapper<>(
            cell.getValue().getTotal()
        )
    );

    tablesTable.setItems(viewModel.getTables());

    viewModel.loadFromModel();
  }

  public void setTabsController(TabsViewController tabsViewController)
  {
    this.tabsViewController = tabsViewController;
  }

  @FXML
  private void onTableSelected()
  {
    openSelectedTable();
  }

  @FXML
  private void showOrdersButton()
  {
    openSelectedTable();
  }

  private void openSelectedTable()
  {
    TableRow selected =
        tablesTable.getSelectionModel().getSelectedItem();

    if (selected != null && tabsViewController != null)
    {
      tabsViewController.showTableOrdersView(selected.getTableNumber());
    }
  }

  public void reset()
  {
    viewModel.clear();
  }
}