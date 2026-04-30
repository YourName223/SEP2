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

  public String toString()
  {
    return item.getName() + " : " + quantity + " * " + item.getPrice();
  }
}
