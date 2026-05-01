package model;

import java.util.ArrayList;

public class Order
{
  private String content;
  private String orderType;
  private ArrayList<OrderItem> items;

  public Order(String content)
  {
    this.content = content;
  }

  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }

  public String getContent()
  {
    return content;
  }

  public String getOrderType()
  {
    return orderType;
  }

  public void setProducts(ArrayList<OrderItem> items)
  {
    this.items = items;
  }

  public void addProduct(MenuItem menuItem)
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
    StringBuilder string = new StringBuilder("{");

    for (OrderItem orderItem : items)
    {
      string.append("{").append(orderItem.toString()).append("},");
    }

    if (string.length() > 1)
      string.setLength(string.length() - 1);

    string.append("}");

    return string.toString();
  }
}
