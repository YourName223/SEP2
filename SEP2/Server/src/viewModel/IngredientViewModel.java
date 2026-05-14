package viewModel;

import javafx.beans.property.*;
import model.Ingredient;

public class IngredientViewModel
{
  private final StringProperty nameProperty = new SimpleStringProperty();
  private final DoubleProperty stockProperty = new SimpleDoubleProperty();
  private final StringProperty unitProperty = new SimpleStringProperty();
  private final String ingredientId;

  public IngredientViewModel(Ingredient ingredient)
  {
    this.nameProperty.set(ingredient.getName());
    this.stockProperty.set(ingredient.getStock());
    this.unitProperty.set("kg");
    this.ingredientId = ingredient.getId();
  }

  public StringProperty getNameProperty() { return nameProperty; }
  public DoubleProperty getStockProperty() { return stockProperty; }
  public StringProperty getUnitProperty() { return unitProperty; }

  public String getId()
  {
    return ingredientId;
  }
}
