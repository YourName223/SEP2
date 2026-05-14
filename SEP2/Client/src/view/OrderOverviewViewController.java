package view;

import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewModel.OrderContentsViewModel;
import viewModel.OrderItemRowStatusViewModel;
import viewModel.OrderItemViewModel;

public class OrderOverviewViewController
{
  @FXML private TableView<OrderItemRowStatusViewModel> orderTable;
  @FXML private TableColumn<OrderItemRowStatusViewModel, String> nameColumn;
  @FXML private TableColumn<OrderItemRowStatusViewModel, Double> priceColumn;
  @FXML private TableColumn<OrderItemRowStatusViewModel, Integer> qtyColumn;
  @FXML private TableColumn<OrderItemRowStatusViewModel, String> timeColumn;
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

    timeColumn.setCellValueFactory(
        cell -> cell.getValue().timeProperty());

    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    successLabel.textProperty().bind(viewModel.getSuccessProperty());
    qtyLabel.textProperty().bind(viewModel.getAmountProperty().asString());

    orderTable.setItems(viewModel.getOrderItems());

    viewModel.loadFromModel();

    orderTable.setRowFactory(tv -> new TableRow<OrderItemRowStatusViewModel>()
    {
      @Override protected void updateItem(OrderItemRowStatusViewModel item, boolean empty)
      {
        super.updateItem(item, empty);

        if (item == null || empty)
        {
          setStyle("");
          return;
        }

        if (!item.isSelectable())
        {
          setStyle("-fx-text-fill: blue;");
        }
        else
        {
          setStyle("");
        }
      }
    });

    orderTable.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldVal, newVal) ->
        {
          if (newVal != null && !newVal.isSelectable())
          {
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
    OrderItemViewModel selected = orderTable.getSelectionModel().getSelectedItem();

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

  public void cancelButton(ActionEvent actionEvent)
  {
    viewModel.cancelOrder();
  }
}
