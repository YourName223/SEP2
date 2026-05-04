package model;

import java.util.ArrayList;
import java.util.List;

public class OrderManager
{
  private Order order;

  public OrderManager()
  {
    order = new Order(null);
  }

  public Order createOrder()
  {
    return new Order("An order");
  }

  public void addMenuItemToOrder(MenuItem menuItem, int amount)
  {
    order.addMenuItem(menuItem,amount);
  }

  public void setMenuItomOnOrder(MenuItem menuItem, int amount)
  {
    order.setMenuItem(menuItem,amount);
  }

  public List<OrderItemDto> getOrderItemDto()
  {
    List<OrderItemDto> items = new ArrayList<>();

    for(OrderItem orderItem : order.getItems())
    {
      items.add(new OrderItemDto(orderItem.getItem().getName(),orderItem.getQuantity()));
    }

    return items;
  }

  public void removeMenuItemFromOrder(MenuItem menuItem)
  {
    order.removeMenuItem(menuItem);
  }

  public Order getOrder()
  {
    return order;
  }
}
