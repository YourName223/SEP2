package model;

import java.util.ArrayList;

public class OrderManager
{
  private Order order;

  public OrderManager()
  {
    order = new Order();
  }

  public Order createOrder()
  {
    order = new Order();
    return order;
  }

  public void addToOrder(MenuItemDto menuItem, int amount)
  {
    order.addMenuItem(menuItem,amount);
  }

  public void updateOrderItem(MenuItemDto menuItem, int amount)
  {
    order.setItem(menuItem,amount);
  }

  public void removeFromOrder(MenuItemDto menuItem)
  {
    order.removeItem(menuItem);
  }

  public void removeOrder()
  {
    order = new Order();
  }

  public ArrayList<OrderItemDto> getOrderItemDto()
  {
    ArrayList<OrderItemDto> items = new ArrayList<>();

    for(OrderItem orderItem : order.getItems())
    {
      items.add(new OrderItemDto(orderItem.getMenuItem().getName(),orderItem.getQuantity()));
    }

    return items;
  }

  public Order getOrder()
  {
    return order;
  }
}
