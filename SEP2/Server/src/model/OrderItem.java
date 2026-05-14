package model;

public class OrderItem
{
  MenuItem item;
  int quantity;

  public OrderItem(MenuItem item)
  {
    this.item = item;
    quantity = 1;
  }

  public void add()
  {
    quantity++;
  }

  public MenuItem getItem()
  {
    return item;
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
    StringBuilder string = new StringBuilder("");

    string.append(quantity + "pc : " + item.getName() + " " + item.getPrice() + "DKK " + item.recipesString());

    return string.toString();
  }

  @Override public boolean equals(Object obj)
  {
    if(this == obj)
      return true;

    if(obj == null || getClass() != obj.getClass())
      return false;

    OrderItem orderItem = (OrderItem) obj;

    return this.item.equals(orderItem.getItem()) && this.quantity==orderItem.getQuantity();
  }
}