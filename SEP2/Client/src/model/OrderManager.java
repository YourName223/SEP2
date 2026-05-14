package model;

import java.util.ArrayList;

public class OrderManager
{
  private ArrayList<Order> orders = new ArrayList<>();
  private Order order = new Order();

  public Order createOrder()
  {
    orders = new ArrayList<>();
    order = new Order();
    return order;
  }

  public Order getOrder()
  {
    return order;
  }

  public ArrayList<Order> getOldOrders()
  {
    return orders;
  }

  public void acceptOrder()
  {
    orders.add(order);
    order = new Order();
  }

  public void removeOrder()
  {
    order = new Order();
  }

  public void removeOrder(Order order)
  {
    orders.remove(order);
  }

  public void addToOrder(MenuItemDto menuItem, int amount)
  {
    if (menuItem.getStock() < amount)
      throw new IllegalStateException("Not enough stock for: " + menuItem.getName());

    order.addMenuItem(menuItem, amount);
  }

  public void updateOrderItem(MenuItemDto menuItem, int amount)
  {
    order.setItem(menuItem, amount);
  }

  public void removeFromOrder(MenuItemDto menuItem)
  {
    order.removeMenuItem(menuItem);
  }

  public void removeAllOrders()
  {
    orders.clear();
    order = new Order();
  }

  public ArrayList<OrderItemDto> getOrderItemDto()
  {
    return convertOrderToOrderItemDto(order);
  }

  public ArrayList<OrderItemDto> convertOrderToOrderItemDto(Order order)
  {
    ArrayList<OrderItemDto> items = new ArrayList<>();

    for (OrderItem orderItem : order.getItems())
    {
      items.add(new OrderItemDto(
          orderItem.getMenuItem().getName(),
          orderItem.getQuantity()
      ));
    }

    return items;
  }

  public OrderItemDto convertOrderItemToOrderItemDto(OrderItem orderItem)
  {
    return new OrderItemDto(
        orderItem.getMenuItem().getName(),
        orderItem.getQuantity());
  }
}