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
import viewModel.OrderItemViewModel;

public class OrderOverviewViewController
{
  @FXML private TableView<OrderItemViewModel> orderTable;
  @FXML private TableColumn<OrderItemViewModel, String> nameColumn;
  @FXML private TableColumn<OrderItemViewModel, Double> priceColumn;
  @FXML private TableColumn<OrderItemViewModel, Integer> qtyColumn;
  @FXML private TableColumn<OrderItemViewModel, String> timeColumn;
  @FXML private TableView<OrderItemViewModel> orderTable1;
  @FXML private TableColumn<OrderItemViewModel, String> nameColumn1;
  @FXML private TableColumn<OrderItemViewModel, Double> priceColumn1;
  @FXML private TableColumn<OrderItemViewModel, Integer> qtyColumn1;
  @FXML private TableColumn<OrderItemViewModel, String> timeColumn1;
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
 cell -> cell.getValue().waitingTimeProperty());

    nameColumn1.setCellValueFactory(
        cell -> new SimpleStringProperty(cell.getValue().getName()));

    priceColumn1.setCellValueFactory(
        cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getPrice()));

    qtyColumn1.setCellValueFactory(
        cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getQuantity()));

    timeColumn1.setCellValueFactory(
        cell -> cell.getValue().waitingTimeProperty());

    orderTable1.setItems(viewModel.getOldOrderItems());
    orderTable1.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldVal, newVal) ->
        {
          if (newVal != null)
            viewModel.setSelectedOrderItem(newVal.getOrderItem());
        });

    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    successLabel.textProperty().bind(viewModel.getSuccessProperty());
    qtyLabel.textProperty().bind(viewModel.getAmountProperty().asString());

    orderTable.setItems(viewModel.getOrderItems());

    viewModel.loadFromModel();

    orderTable.setRowFactory(tv -> new TableRow<OrderItemViewModel>()
    {
      @Override protected void updateItem(OrderItemViewModel item, boolean empty)
      {
        super.updateItem(item, empty);

        if (item == null || empty)
        {
          setStyle("");
          return;
        }

        //if (!item.isSelectable())
        //{
        //  setStyle("-fx-text-fill: blue;");
        //}
        else
        {
          setStyle("");
        }
      }
    });

    orderTable.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldVal, newVal) ->
        {
         // if (newVal != null && !newVal.isSelectable())
         // {
         //   orderTable.getSelectionModel().clearSelection();
         // }
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
      orderTable1.getSelectionModel().clearSelection();
      viewModel.setSelectedOrderItem(selected.getOrderItem());
      return;
    }

    OrderItemViewModel selected1 = orderTable1.getSelectionModel().getSelectedItem();
    if (selected1 != null)
    {
      orderTable.getSelectionModel().clearSelection();
      viewModel.setSelectedOrderItem(selected1.getOrderItem());
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
