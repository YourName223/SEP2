package view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
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

    viewModel.loadFromModel();
  }

  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
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
