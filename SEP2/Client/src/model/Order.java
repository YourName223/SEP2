package model;

import java.util.ArrayList;

public class Order
{
  private String content;
  private ArrayList<OrderItem> items;

  public Order(String content)
  {
    this.content = content;
    items = new ArrayList<>();
  }

  public String getContent()
  {
    return content;
  }

  public void setProduct(MenuItem menuItem, int amount)
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
        {
          if(amount == 0)
          {
            items.remove(item);
          }
          else
          {
            item.setQuantity(amount);
          }
        }
      }
    }
    else
    {
      items.add(new OrderItem(menuItem,amount));
    }
  }

  public void removeProduct(MenuItem menuItem)
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
        if(item.getItem().equals(menuItem) && item.getQuantity() > 1)
          item.remove();
        else
          items.remove(item);
      }
    }
  }

  public ArrayList<OrderItem> getItems()
  {
    return items;
  }
}
