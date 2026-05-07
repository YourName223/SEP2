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

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class OrderManager {

  private OrderListCurrent orderList;
  private MenuManager menuManager;

  public OrderManager(MenuManager menuManager) {
    this.menuManager = menuManager;
    orderList = new OrderListCurrent();
  }

  public void addOrder(Order order) {
    orderList.addOrder(order);
    new OrderPrinter().printOrder(order);
  }

  public void clickOnOrder(OrderCurrent order)
  {
    orderList.click(order);
  }

  public OrderListCurrent getOrderList() {
    return orderList;
  }

  public TableOrder createTableOrder(Order order, String tableNr) {
    return new TableOrder(order, tableNr);
  }

  public Order convertOrderDtoToOrder(OrderDto orderDto) {
    Order order = new Order();
    ArrayList<OrderItem> items = new ArrayList<>();

    for (OrderItemDto orderItemDto : orderDto.items) {
      MenuItem menuItem = menuManager.getMenuItemById(orderItemDto.getMenuItemId());
      OrderItem orderItem = new OrderItem(menuItem);
      orderItem.setQuantity(orderItemDto.getQuantity());
      items.add(orderItem);
    }

    order.setOrderItems(items);
    return order;
  }
}