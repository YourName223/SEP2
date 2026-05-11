package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderItemRow
{
  private final StringProperty quantity = new SimpleStringProperty();
  private final StringProperty name = new SimpleStringProperty();
  private final StringProperty price = new SimpleStringProperty();

  public OrderItemRow(int quantity, String name, double price)
  {
    this.quantity.set(quantity + "x");
    this.name.set(name);
    this.price.set(String.format("%.2f", price));
  }

  public StringProperty quantityProperty() { return quantity; }
  public StringProperty nameProperty() { return name; }
  public StringProperty priceProperty() { return price; }
}
