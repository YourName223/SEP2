package model;

import java.util.ArrayList;

public class Order
{
  private String orderType;
  private ArrayList<OrderItem> items;

  public Order()
  {
    items = new ArrayList<>();
  }

  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }

  public String getOrderType()
  {
    return orderType;
  }

  public void setOrderItems(ArrayList<OrderItem> items)
  {
    this.items = items;
  }

  public void addMenuItem(MenuItem menuItem)
  {
    boolean partOfList = false;

    for(OrderItem item : items)
    {
      if(item.getItem().equals(menuItem))
        partOfList = true;
    }

    if(partOfList)
    {
      for (OrderItem item : items)
      {
        if(item.getItem().equals(menuItem))
          item.add();
      }
    }
    else
    {
      items.add(new OrderItem(menuItem));
    }
  }

  public ArrayList<OrderItem> getOrderItems()
  {
    return items;
  }

  public ArrayList<MenuItem> getMenuItems()
  {
    ArrayList<MenuItem> menuItems = new ArrayList<>();
    for (OrderItem orderItem : getOrderItems())
    {
      menuItems.add(orderItem.getItem());
    }
    return menuItems;
  }

  public String toString()
  {
    StringBuilder string = new StringBuilder("[");
    for (OrderItem orderItem : items)
    {
      string.append("{").append(orderItem).append("},");
    }
    if (string.length() > 1)
      string.setLength(string.length() - 1);
    string.append("]");
    return string.toString();
  }

  public double getTotalPrice()
  {
    double price = 0;
    for (OrderItem orderItem : getOrderItems())
    {
      price += (orderItem.getQuantity()*orderItem.getItem().getPrice());
    }
    return price;
  }

  public void removeOrderItem(OrderItem orderItem)
  {
    items.remove(orderItem);
  }
}
