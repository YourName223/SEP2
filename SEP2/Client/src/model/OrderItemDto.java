package model;

public class OrderItemDto
{
  private String menuItemId;
  private int quantity;

  public OrderItemDto(String menuItemId, int quantity)
  {
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
}
