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

  public void setMenuItems(ArrayList<OrderItem> items)
  {
    this.items = items;
  }

  public void addMenuItem(MenuItem menuItem)
  {
    boolean partOfList = false;

    for (OrderItem item : items)
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

  public ArrayList<OrderItem> getItems()
  {
    return items;
  }

  public String toString()
  {
    StringBuilder string = new StringBuilder("[");
    System.out.println(items.size());
    for (OrderItem orderItem : items)
    {
      System.out.println(orderItem.getItem().getName());
      string.append("{").append(orderItem).append("},");
    }
    if (string.length() > 1)
      string.setLength(string.length() - 1);
    string.append("]");
    return string.toString();
  }
}
