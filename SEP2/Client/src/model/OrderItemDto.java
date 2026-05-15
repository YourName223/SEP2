package model;

public class OrderItemDto
{
  private String menuItemId;
  private boolean active;
  private int quantity;

  public OrderItemDto(String menuItemId, int quantity)
  {
    active = false;
    this.menuItemId = menuItemId;
    this.quantity = quantity;
  }

  public String getMenuItemId()
  {
    return menuItemId;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void setQuantity(int quantity)
  {
    this.quantity = quantity;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

  @Override public boolean equals(Object obj)
  {
    if(this == obj)
      return true;

    if(obj == null || getClass() != obj.getClass())
      return false;

    OrderItemDto orderItemDto = (OrderItemDto) obj;

    return (this.menuItemId.equals(orderItemDto.menuItemId) && this.quantity==orderItemDto.getQuantity() && this.isActive()==orderItemDto.isActive());
  }
}
