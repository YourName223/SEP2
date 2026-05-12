package view;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import viewModel.OrderItemRow;
import viewModel.TableOrdersViewModel;

public class TableOrdersViewController
    implements ViewController<TableOrdersViewModel>
{
  @FXML private VBox ordersBox;
  @FXML private Label tableNumberLabel;
  @FXML private Label totalLabel;
  @FXML private Label successLabel;
  @FXML private Label errorLabel;

  private TableOrdersViewModel viewModel;
  private TabsViewController tabsViewController;

  @Override
  public void init(TableOrdersViewModel viewModel)
  {
    this.viewModel = viewModel;

    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    successLabel.textProperty().bind(viewModel.getSuccessProperty());

    tableNumberLabel.textProperty().bind(viewModel.getTableNumber());

    totalLabel.textProperty().bind(
        Bindings.format("%.2f", viewModel.getTotal())
    );

    viewModel.getRows().addListener((Observable obs) -> renderRows());

    viewModel.loadFromModel();
    renderRows();
  }

  public void setTabsController(TabsViewController tabsViewController)
  {
    this.tabsViewController = tabsViewController;
  }

  public void reset()
  {
    viewModel.clear();
  }

  private void renderRows()
  {
    ordersBox.getChildren().clear();

    for (OrderItemRow rowModel : viewModel.getRows())
    {
      HBox row = new HBox(10);

      Label qty = new Label();
      qty.textProperty().bind(rowModel.quantityProperty());

      Label name = new Label();
      name.textProperty().bind(rowModel.nameProperty());

      Label price = new Label();
      price.textProperty().bind(rowModel.priceProperty());

      Region space = new Region();
      HBox.setHgrow(space, Priority.ALWAYS);

      row.getChildren().addAll(qty, name, space, price);
      ordersBox.getChildren().add(row);
    }
  }

  @FXML
  private void resetOrdersButton()
  {
    viewModel.resetOrders();
  }

  @FXML
  private void backToTablesButton()
  {
    if (tabsViewController != null)
    {
      tabsViewController.showTablesView();
    }
  }
}