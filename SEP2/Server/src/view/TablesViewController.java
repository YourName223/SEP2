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
    setSelectedTable();
  }

  @FXML
  private void showOrdersButton()
  {
    TableRow selected = tablesTable.getSelectionModel().getSelectedItem();

    if (selected != null)
    {
      viewModel.openTable(selected.getTableNumber());
    }
    System.out.println("Tabs controller = " + tabsViewController);
  }

  private void setSelectedTable()
  {
    TableRow selected =
        tablesTable.getSelectionModel().getSelectedItem();

    if (selected != null && tabsViewController != null)
    {
      tabsViewController.showTableOrdersView(selected.getTableNumber());
    }
    System.out.println("Table selected " + selected);
  }

  public void reset()
  {
    viewModel.clear();
  }
}