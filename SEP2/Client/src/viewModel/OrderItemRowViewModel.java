package viewModel;

public class OrderItemRowViewModel
{
  private final OrderItemViewModel item;
  private final boolean selectable;

  public OrderItemRowViewModel(OrderItemViewModel item, boolean selectable) {
    this.item = item;
    this.selectable = selectable;
  }

  public OrderItemViewModel getItem() {
    return item;
  }

  public boolean isSelectable() {
    return selectable;
  }

  public String getName() { return item.getName(); }
  public double getPrice() { return item.getPrice(); }
  public int getQuantity() { return item.getQuantity(); }
}
