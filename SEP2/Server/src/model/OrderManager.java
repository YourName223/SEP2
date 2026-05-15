/*package model;

import java.util.ArrayList;

public class OrderManager
{
  private OrderListCurrent orderList;

  private MenuManager menuManager;

  public OrderManager(MenuManager menuManager)
  {
    this.menuManager = menuManager;
    orderList = new OrderListCurrent();
  }

  public TableOrder createTableOrder(Order order, String tableNr)
  {
    return new TableOrder(order,tableNr);
  }

  public void addOrder(Order order)
  {
    orderList.addOrder(order);
    new OrderPrinter().printOrder(order);
  }

  public OrderListCurrent getOrderList()
  {
    return orderList;
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

    order.setOrderItems(items);

    return order;
  }
}*/

package model;

import java.util.ArrayList;

public class OrderManager
{
  private OrderListCurrent orderList;
  private MenuManager menuManager;
  private int followingOrderId;

  public OrderManager(MenuManager menuManager)
  {
    this.menuManager = menuManager;
    orderList = new OrderListCurrent();
  }

  public int getFollowingOrderId()
  {
    return followingOrderId;
  }

  private void updateFollowingOrderId()
  {
    followingOrderId++;
  }

  public void addOrder(Order order)
  {
    orderList.addOrder(order);
    updateFollowingOrderId();
  }

  public void clickOnOrder(OrderCurrent order)
  {
    orderList.click(order);
  }

  public void removeOrder(OrderCurrent order)
  {
    orderList.removeOrder(order);
  }

  public OrderListCurrent getOrderList()
  {
    return orderList;
  }

  public TableOrder createTableOrder(Order order, String tableNr)
  {
    return new TableOrder(order, tableNr);
  }

  public Order convertOrderDtoToOrder(OrderDto orderDto)
  {
    Order order = new Order();
    ArrayList<OrderItem> items = new ArrayList<>();

    for (OrderItemDto orderItemDto : orderDto.items)
    {
      MenuItem menuItem = menuManager.getMenuItemById(orderItemDto.getMenuItemId());
      OrderItem orderItem = new OrderItem(menuItem);
      orderItem.setQuantity(orderItemDto.getQuantity());
      orderItem.setActive(orderItemDto.isActive());
      items.add(orderItem);
    }

    order.setOrderItems(items);

    return order;
  }

  public ArrayList<OrderItemDto> convertOrderToOrderItemDto(Order order)
  {
    ArrayList<OrderItemDto> items = new ArrayList<>();

    for (OrderItem orderItem : order.getOrderItems())
    {
      OrderItemDto orderItemDto = new OrderItemDto(
          orderItem.getItem().getName(),
          orderItem.getQuantity());
      orderItemDto.setActive(orderItem.isActive());
      items.add(orderItemDto);
    }

    return items;
  }

  public void removeOrderItem(Order order, OrderItem orderItem)
  {
    orderList.removeOrderItem(order, orderItem);
  }
}