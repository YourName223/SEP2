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
}