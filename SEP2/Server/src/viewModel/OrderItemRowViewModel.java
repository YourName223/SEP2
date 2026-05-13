package viewModel;

import javafx.beans.property.*;

public class OrderItemRowViewModel
{
  private final IntegerProperty quantityProperty = new SimpleIntegerProperty();
  private final StringProperty nameProperty = new SimpleStringProperty();
  private final DoubleProperty priceProperty = new SimpleDoubleProperty();

  public OrderItemRowViewModel(int quantityProperty, String nameProperty, double priceProperty)
  {
    this.quantityProperty.set(quantityProperty);
    this.nameProperty.set(nameProperty);
    this.priceProperty.set(priceProperty);
  }

  public IntegerProperty getQuantityProperty() { return quantityProperty; }
  public StringProperty getNameProperty() { return nameProperty; }
  public DoubleProperty getPriceProperty() { return priceProperty; }
}
