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

  public void acceptOrder(int id)
  {
    order.setId(id);
    orders.add(order);
    order = new Order();
  }

  public void removeOrder()
  {
    order = new Order();
  }

  public void removeOrder(int id)
  {
    orders.remove(getOrderFromId(id));
  }

  public Order getOrderFromId(int id)
  {
    System.out.println(id);
    for (Order order : orders)
    {
      System.out.println(order.getId());
      if (order.getId() == id)
      {
        return order;
      }
    }
    throw new IllegalArgumentException("Order does not exist");
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
    OrderItemDto orderItemDto = new OrderItemDto(orderItem.getMenuItem().getName(),orderItem.getQuantity());
    orderItemDto.setActive(orderItem.isActive());

    return orderItemDto;
  }

  public Order getOrderFromOrderItemDto(OrderItemDto orderItemDto)
  {
    for (Order order : getOldOrders())
    {
      ArrayList<OrderItem> orderItems = order.getItems();
      for (OrderItem orderItem : orderItems)
      {
        if (convertOrderItemToOrderItemDto(orderItem).equals(orderItemDto))
        {
          return order;
        }
      }
    }
    return null;
  }

  public OrderItem getOrderItemFromOrderItemDto(OrderItemDto orderItemDto)
  {
    for (Order order : getOldOrders())
    {
      ArrayList<OrderItem> orderItems = order.getItems();
      for (OrderItem orderItem : orderItems)
      {
        if (convertOrderItemToOrderItemDto(orderItem).equals(orderItemDto))
        {
          return orderItem;
        }
      }
    }
    return null;
  }

  public Order getOrderFromOrderItemDtos(ArrayList<OrderItemDto> orderItemDtos)
  {
    for (Order order : getOldOrders())
    {
      if(convertOrderToOrderItemDto(order).equals(orderItemDtos))
      {
        return order;
      }
    }
    return null;
  }
}