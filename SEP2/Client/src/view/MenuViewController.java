package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewModel.MenuViewModel;
import viewModel.OrderViewModel;

public class MenuViewController
{
  @FXML private TableView<MenuViewModel> menuTable;
  @FXML private TableColumn<MenuViewModel,StringProperty> nameColumn;
  @FXML private TableColumn<MenuViewModel,StringProperty> allergensColumn;
  @FXML private TableColumn<MenuViewModel,DoubleProperty> priceColumn;
  @FXML private Label qtyLabel;
  @FXML private Label totalLabel;

  private ViewHandler viewHandler;
  private MenuViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, MenuViewModel viewModel, Region root)
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
  private void addToOrderButton() {

  }

  @FXML
  private void viewOrderButton() {
    viewHandler.openPopup("OrderOverviewView.fxml");
  }
}
