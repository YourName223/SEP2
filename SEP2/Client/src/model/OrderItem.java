package model;

public class OrderItem
{
  private MenuItemDto menuItem;
  private int quantity;

  public OrderItem(MenuItemDto menuItem,int quantity)
  {
    this.menuItem = menuItem;
    this.quantity = quantity;
  }

  public MenuItemDto getMenuItem()
  {
    return menuItem;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void setQuantity(int quantity)
  {
    this.quantity = quantity;
  }

  public String toString()
  {
    return menuItem.getName() + " : " + quantity;
  }

  @Override public boolean equals(Object obj)
  {
    if(this == obj)
      return true;

    if(obj == null || getClass() != obj.getClass())
      return false;

    OrderItem orderItem = (OrderItem) obj;

    return this.menuItem.equals(orderItem.getMenuItem()) && this.quantity==orderItem.getQuantity();
  }
}
