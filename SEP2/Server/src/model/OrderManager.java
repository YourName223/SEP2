package model;

import java.util.ArrayList;

public class OrderManager
{
  private ArrayList<Order> orders;
  private MenuManager menuManager;

  public OrderManager(MenuManager menuManager)
  {
    this.menuManager = menuManager;
    orders = new ArrayList<>();
  }

  public TableOrder createTableOrder(Order order, String tableNr)
  {
    return new TableOrder(order,tableNr);
  }

  public void addOrder(Order order)
  {
    orders.add(order);
    new OrderPrinter().printOrder(order);
  }

  public Order convertOrderDtoToOrder(OrderDto orderDto)
  {
    Order order = new Order();

    ArrayList<OrderItem> items = new ArrayList<>();

    for(OrderItemDto orderItemDto : orderDto.items)
    {
      MenuItem menuItem = menuManager.getMenuItemById(orderItemDto.getMenuItemId());

      OrderItem orderItem = new OrderItem(menuItem);
      orderItem.setQuantity(orderItemDto.getQuantity());

      items.add(orderItem);
    }

    order.setMenuItems(items);

    return order;
  }
}
