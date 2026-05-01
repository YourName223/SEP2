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

  public void addProduct(MenuItem menuItem, int amount)
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
          item.setQuantity(amount+item.getQuantity());
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
    for (OrderItem item : items)
    {
      if(item.getItem().equals(menuItem))
      {
        item.remove();
        break;
      }
    }
  }

  public ArrayList<OrderItem> getItems()
  {
    return items;
  }
}
