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

  private TableRow selectedTable;

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
    selectedTable = tablesTable.getSelectionModel().getSelectedItem();
    System.out.println("Selected table = " + selectedTable);
  }

  @FXML
  private void showOrdersButton()
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
      System.out.println("TabsViewController is null");
      return;
    }

    tabsViewController.showTableOrdersView(
        selectedTable.getTableNumber()
    );
  }

  public void reset()
  {
    viewModel.clear();
  }
}