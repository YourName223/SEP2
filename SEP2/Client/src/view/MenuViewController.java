package view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import viewModel.MenuListViewModel;
import viewModel.MenuViewModel;

public class MenuViewController
{
  @FXML private TableView<MenuViewModel> menuTable;
  @FXML private TableColumn<MenuViewModel, String> nameColumn;
  @FXML private TableColumn<MenuViewModel, String> allergensColumn;
  @FXML private TableColumn<MenuViewModel, Double> priceColumn;
  @FXML private Label qtyLabel;
  @FXML private Label totalLabel;
  @FXML private Label errorLabel;

  private ViewHandler viewHandler;
  private MenuListViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, MenuListViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    nameColumn.setCellValueFactory(
        cell -> new SimpleStringProperty(cell.getValue().getName()));
    allergensColumn.setCellValueFactory(cell -> new SimpleStringProperty(
        String.valueOf(cell.getValue().getAllergies())));
    priceColumn.setCellValueFactory(
        cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getPrice()));

    menuTable.setItems(viewModel.getMenuItems());

    qtyLabel.textProperty().bind(viewModel.getAmountProperty().asString());

    errorLabel.textProperty().bind(viewModel.getErrorProperty());
  }

  public void reset()
  {
    viewModel.loadFromModel();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void onMenuItemSelected()
  {
    MenuViewModel selected = menuTable.getSelectionModel().getSelectedItem();
    if (selected != null)
    {
      viewModel.selectMenuItem(selected.getMenuItem());
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

  @FXML private void addToOrderButton()
  {
    viewModel.addToOrder();
  }

  @FXML private void viewOrderButton()
  {
    viewHandler.openPopup("OrderOverviewView.fxml");
  }
}
