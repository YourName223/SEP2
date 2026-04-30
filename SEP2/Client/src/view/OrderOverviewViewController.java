package view;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewModel.MenuViewModel;
import viewModel.OrderContentsViewModel;
import viewModel.OrderItemViewModel;
import viewModel.OrderViewModel;

public class OrderOverviewViewController
{
  @FXML private TableView<OrderItemViewModel> orderTable;
  @FXML private TableColumn<OrderItemViewModel, String> nameColumn;
  @FXML private TableColumn<OrderItemViewModel, Double> priceColumn;
  @FXML private TableColumn<OrderItemViewModel, Integer> qtyColumn;
  @FXML private Label qtyLabel;
  @FXML private Label totalLabel;
  @FXML private Label successLabel;
  @FXML private Label errorLabel;

  private ViewHandler viewHandler;
  private OrderContentsViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, OrderContentsViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    nameColumn.setCellValueFactory(
        cell -> new SimpleStringProperty(cell.getValue().getName()));
    priceColumn.setCellValueFactory(
        cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getPrice()));
    qtyColumn.setCellValueFactory(
        cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getQuantity()));

    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    successLabel.textProperty().bind(viewModel.getSuccessProperty());

    orderTable.setItems(viewModel.getOrderItems());

  }

  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void onOrderItemSelected()
  {
    System.out.println("Selected an item");
    OrderItemViewModel selected = orderTable.getSelectionModel()
        .getSelectedItem();
    if (selected != null)
    {
      viewModel.setSelectedOrderItem(selected.getOrderItem());
    }
  }

  @FXML private void increaseButton()
  {
    viewModel.increase();
  }

  @FXML private void decreaseButton()
  {
    viewModel.decrease();
  }

  @FXML private void updateButton()
  {
    viewModel.updateQuantity();
  }

  @FXML private void removeButton()
  {
    viewModel.deleteMenuItem();
  }

  @FXML private void placeOrderButton()
  {
    viewModel.placeOrder();
  }
}
