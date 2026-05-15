package model;

import java.util.ArrayList;

public class Order
{
  private ArrayList<OrderItem> items;
  private int id;

  public Order()
  {
    items = new ArrayList<>();
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getId()
  {
    return id;
  }

  public void setItem(MenuItemDto menuItem, int amount)
  {
    OrderItem existing = find(menuItem);

    if (existing != null)
    {
      if (amount == 0)
      {
        items.remove(existing);
      }
      else
      {
        existing.setQuantity(amount);
      }
    }
    else if (amount > 0)
    {
      items.add(new OrderItem(menuItem, amount));
    }
  }

  public void addMenuItem(MenuItemDto menuItem, int amount)
  {
    OrderItem existing = find(menuItem);
    int currentAmount = 0;

    if(existing != null)
    {
      currentAmount = existing.getQuantity();
    }
    setItem(menuItem,amount+currentAmount);
  }

  public void removeMenuItem(MenuItemDto menuItem)
  {
    OrderItem existing = find(menuItem);

    if(existing != null)
    {
      items.remove(existing);
    }
  }

  public void removeOrderItem(OrderItem orderItem)
  {
    items.remove(orderItem);
  }

  public ArrayList<OrderItem> getItems()
  {
    return items;
  }

  private OrderItem find(MenuItemDto menuItemDto)
  {
    for (OrderItem item : items)
    {
      if (item.getMenuItem().equals(menuItemDto))
        return item;
    }
    return null;
  }
}
