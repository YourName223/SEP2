package view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewModel.InventoryViewModel;
import viewModel.TableRowViewModel;
import viewModel.TablesViewModel;

public class InventoryViewController implements ViewController<InventoryViewModel>
{
  @FXML private TableView<TableRowViewModel> ingredientTable;
  @FXML private TableColumn<TableRowViewModel, String> ingredientNameColumn;
  @FXML private TableColumn<TableRowViewModel, Double> ingredientStockColumn;
  @FXML private TableColumn<TableRowViewModel, Double> ingredientUnitColumn;

  private InventoryViewModel viewModel;
  private TabsViewController tabsViewController;

  private TableRowViewModel selectedIngredient;

  @Override public void init(InventoryViewModel viewModel)
  {
    this.viewModel = viewModel;

    ingredientNameColumn.setCellValueFactory(
        cell -> cell.getValue().getTableNrProperty());

    ingredientStockColumn.setCellValueFactory(
        cell -> cell.getValue().getTableNrProperty());

    ingredientUnitColumn.setCellValueFactory(
        cell -> cell.getValue().getTableNrProperty());

    ingredientTable.setItems(viewModel.getIngredients());
    viewModel.loadFromModel();
  }


  @FXML private void onIngredientSelected()
  {
    selectedIngredient = ingredientTable.getSelectionModel().getSelectedItem();
  }

  @FXML private void updateStockButton()
  {
    viewModel.updateStock();
  }

  public void reset()
  {
    viewModel.clear();
  }
}
