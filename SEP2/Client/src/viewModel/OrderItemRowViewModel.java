package viewModel;

import model.OrderItem;

public class OrderItemRowViewModel
{
  private final OrderItemViewModel itemVM;
  private final boolean selectable;

  public OrderItemRowViewModel(OrderItemViewModel itemVM, boolean selectable)
  {
    this.itemVM = itemVM;
    this.selectable = selectable;
  }

  public boolean isSelectable()
  {
    return selectable;
  }

  public String getName()
  {
    return itemVM.getName();
  }

  public double getPrice()
  {
    return itemVM.getPrice();
  }

  public int getQuantity()
  {
    return itemVM.getQuantity();
  }

  public OrderItem getOrderItem()
  {
    return itemVM.getOrderItem();
  }
}