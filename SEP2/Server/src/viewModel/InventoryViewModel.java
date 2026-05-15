package viewModel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ingredient;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class InventoryViewModel implements PropertyChangeListener
{
  private final Model model;

  private final ObservableList<IngredientViewModel> ingredients = FXCollections.observableArrayList();

  private final StringProperty errorProperty = new SimpleStringProperty("");
  private final StringProperty successProperty = new SimpleStringProperty("");

  private final StringProperty selectedNameProperty = new SimpleStringProperty(
      "");
  private final StringProperty selectedUnitProperty = new SimpleStringProperty(
      "");

  private final StringProperty updatedStockProperty = new SimpleStringProperty(
      "");

  private final BooleanProperty hasSelectionProperty = new SimpleBooleanProperty(
      false);

  private IngredientViewModel selectedIngredient;

  public InventoryViewModel(Model model)
  {
    this.model = model;
    model.addListener("Update",this);
    loadFromModel();
  }

  public ObservableList<IngredientViewModel> getIngredients()
  {
    return ingredients;
  }

  public void loadFromModel()
  {
    ingredients.clear();

    for (Ingredient ingredient : model.getIngredients())
    {
      ingredients.add(new IngredientViewModel(ingredient));
    }
  }

  public void setSelectedIngredient(IngredientViewModel selected)
  {
    this.selectedIngredient = selected;

    if (selected == null)
    {
      hasSelectionProperty.set(false);
      selectedNameProperty.set("");
      selectedUnitProperty.set("");
      updatedStockProperty.set("");
      successProperty.set("");
    }
    else
    {
      hasSelectionProperty.set(true);
      selectedNameProperty.set(selected.getNameProperty().get());
      selectedUnitProperty.set(selected.getUnitProperty().get());
    }
  }

  public void updateStock()
  {
    if (selectedIngredient == null)
    {
      errorProperty.set("No ingredient selected");
      return;
    }

    try
    {
      double newStock = Double.parseDouble(updatedStockProperty.get());

      model.setStockOnIngredient(selectedIngredient.getId(), newStock);

      successProperty.set("Stock updated");
      errorProperty.set("");

      loadFromModel();
    }
    catch (NumberFormatException e)
    {
      errorProperty.set("Invalid number");
    }
  }

  public void clear()
  {
    setSelectedIngredient(null);
    errorProperty.set("");
    successProperty.set("");
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public StringProperty getSuccessProperty()
  {
    return successProperty;
  }

  public StringProperty getSelectedNameProperty()
  {
    return selectedNameProperty;
  }

  public StringProperty getSelectedUnitProperty()
  {
    return selectedUnitProperty;
  }

  public StringProperty getUpdatedStockProperty()
  {
    return updatedStockProperty;
  }

  public BooleanProperty hasSelectionProperty()
  {
    return hasSelectionProperty;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> loadFromModel());
  }
}