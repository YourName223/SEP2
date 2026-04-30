package model;

public class OrderItem
{
  MenuItem item;
  int quantity;

  public OrderItem(MenuItem item,int quantity)
  {
    this.item = item;
    this.quantity = quantity;
  }

  public void add()
  {
    quantity++;
  }

  public void remove()
  {
    quantity--;
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
    return item.getName() + " : " + quantity + " * " + item.getPrice();
  }
}
