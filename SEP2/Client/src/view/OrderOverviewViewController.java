package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewModel.MenuViewModel;
import viewModel.OrderContentsViewModel;
import viewModel.OrderViewModel;

public class OrderOverviewViewController
{
  @FXML private TableView<OrderContentsViewModel> orderTable;
  @FXML private TableColumn<OrderContentsViewModel, StringProperty> nameColumn;
  @FXML private TableColumn<OrderContentsViewModel, DoubleProperty> priceColumn;
  @FXML private TableColumn<OrderContentsViewModel, IntegerProperty> qtyColumn;
  @FXML private Label qtyLabel;
  @FXML private Label totalLabel;

  private ViewHandler viewHandler;
  private OrderViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, OrderViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
  }

  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }


  @FXML
  private void increaseButton() {

  }

  @FXML
  private void decreaseButton() {

  }

  @FXML
  private void updateButton() {

  }

  @FXML
  private void removeButton() {

  }

  @FXML
  private void placeOrderButton() {
    viewModel.placeOrder();
  }
}
