package viewModel;

import javafx.beans.property.*;

public class IngredientViewModel
{
  private final StringProperty nameProperty = new SimpleStringProperty();
  private final DoubleProperty stockProperty = new SimpleDoubleProperty();
  private final StringProperty unitProperty = new SimpleStringProperty();

  public IngredientViewModel(String nameProperty, double stockProperty, String unitProperty)
  {
    this.nameProperty.set(nameProperty);
    this.stockProperty.set(stockProperty);
    this.unitProperty.set(unitProperty);
  }

  public StringProperty getNameProperty() { return nameProperty; }
  public DoubleProperty getStockProperty() { return stockProperty; }
  public StringProperty getUnitProperty() { return unitProperty; }
}
