package view;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewModel.OrderContentsViewModel;
import viewModel.OrderItemRowViewModel;
import viewModel.OrderItemViewModel;

public class OrderOverviewViewController
{
  @FXML private TableView<OrderItemRowViewModel> orderTable;
  @FXML private TableColumn<OrderItemRowViewModel, String> nameColumn;
  @FXML private TableColumn<OrderItemRowViewModel, Double> priceColumn;
  @FXML private TableColumn<OrderItemRowViewModel, Integer> qtyColumn;
  @FXML private Label qtyLabel;
  @FXML private Label totalLabel;
  @FXML private Label successLabel;
  @FXML private Label errorLabel;

  private ViewHandler viewHandler;
  private OrderContentsViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, OrderContentsViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    nameColumn.setCellValueFactory(cell ->
        new SimpleStringProperty(cell.getValue().getName())
    );

    priceColumn.setCellValueFactory(cell ->
        new ReadOnlyObjectWrapper<>(cell.getValue().getPrice())
    );

    qtyColumn.setCellValueFactory(cell ->
        new ReadOnlyObjectWrapper<>(cell.getValue().getQuantity())
    );

    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    successLabel.textProperty().bind(viewModel.getSuccessProperty());
    qtyLabel.textProperty().bind(viewModel.getAmount().asString());

    orderTable.setItems(viewModel.getOrderItems());

    viewModel.loadFromModel();

    orderTable.setRowFactory(tv -> new TableRow<OrderItemRowViewModel>()
    {
      @Override
      protected void updateItem(OrderItemRowViewModel item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
          setStyle("");
          return;
        }

        if (!item.isSelectable()) {
          setStyle("-fx-text-fill: gray;");
        } else {
          setStyle("");
        }
      }
    });

    orderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
      if (newVal != null && !newVal.isSelectable()) {
        orderTable.getSelectionModel().clearSelection();
      }
    });
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
    OrderItemRowViewModel selected = orderTable.getSelectionModel().getSelectedItem();
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
