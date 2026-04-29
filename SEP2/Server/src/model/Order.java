package model;

import java.util.ArrayList;

public abstract class Order
{
  private String content;
  private String orderType;
  private ArrayList<OrderItem> items;

  public Order(String content)
  {
    this.content = content;
    new OrderPrinter().printOrder(this);
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
}
