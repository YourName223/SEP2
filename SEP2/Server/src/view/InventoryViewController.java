package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import viewModel.IngredientViewModel;
import viewModel.InventoryViewModel;

public class InventoryViewController implements ViewController<InventoryViewModel>
{
  @FXML private TableView<IngredientViewModel> ingredientTable;
  @FXML private TableColumn<IngredientViewModel, String> ingredientNameColumn;
  @FXML private TableColumn<IngredientViewModel, Double> ingredientStockColumn;
  @FXML private TableColumn<IngredientViewModel, String> ingredientUnitColumn;

  @FXML private Label ingredientNameLabel;
  @FXML private TextField updatedStockField;
  @FXML private Label unitLabel;
  @FXML private Label successLabel;
  @FXML private Label errorLabel;

  private InventoryViewModel viewModel;


  @Override public void init(InventoryViewModel viewModel)
  {
    this.viewModel = viewModel;

    ingredientNameColumn.setCellValueFactory(
        cell -> cell.getValue().getNameProperty());

    ingredientStockColumn.setCellValueFactory(
        cell -> cell.getValue().getStockProperty().asObject());

    ingredientUnitColumn.setCellValueFactory(
        cell -> cell.getValue().getUnitProperty());

    ingredientTable.setItems(viewModel.getIngredients());

    ingredientNameLabel.textProperty().bind(viewModel.getSelectedNameProperty());
    unitLabel.textProperty().bind(viewModel.getSelectedUnitProperty());

    updatedStockField.textProperty()
        .bindBidirectional(viewModel.getUpdatedStockProperty());

    updatedStockField.visibleProperty()
        .bind(viewModel.hasSelectionProperty());
    updatedStockField.managedProperty()
        .bind(viewModel.hasSelectionProperty());

    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    successLabel.textProperty().bind(viewModel.getSuccessProperty());

    viewModel.loadFromModel();
  }


  @FXML private void onIngredientSelected()
  {
      viewModel.setSelectedIngredient(ingredientTable.getSelectionModel().getSelectedItem());
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
