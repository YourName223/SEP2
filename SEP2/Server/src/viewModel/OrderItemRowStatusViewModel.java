package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderItemRowStatusViewModel
{
  private final StringProperty quantityProperty = new SimpleStringProperty();
  private final StringProperty nameProperty = new SimpleStringProperty();
  private final StringProperty priceProperty = new SimpleStringProperty();

  public OrderItemRowStatusViewModel(int quantityProperty, String nameProperty, double priceProperty)
  {
    this.quantityProperty.set(quantityProperty + "x");
    this.nameProperty.set(nameProperty);
    this.priceProperty.set(String.format("%.2f", priceProperty));
  }

  public StringProperty getQuantityProperty() { return quantityProperty; }
  public StringProperty getNameProperty() { return nameProperty; }
  public StringProperty getPriceProperty() { return priceProperty; }
}
