package view;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import viewModel.OrderItemRow;
import viewModel.TableOrdersViewModel;

public class TableOrdersViewController
{
  @FXML VBox ordersBox;
  @FXML private Label tableNumberLabel;
  @FXML private Label totalLabel;
  @FXML private Label successLabel;
  @FXML private Label errorLabel;

  private ViewHandler viewHandler;
  private TableOrdersViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, TableOrdersViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    successLabel.textProperty().bind(viewModel.getSuccessProperty());
    totalLabel.textProperty().bind(viewModel.getTotal());
    tableNumberLabel.textProperty().bind(viewModel.getTableNumber());

    viewModel.getOrders().addListener((obs) -> renderOrders());

    viewModel.loadFromModel();
    viewModel.getRows().addListener((Observable obs) -> renderRows());
    viewModel.loadFromModel();
    renderRows();
  }
  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
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


  public void backToTablesButton()
  {
    viewModel.backToTables();
  }

  public void resetOrdersButton()
  {
    viewModel.resetOrders();
  }
}
