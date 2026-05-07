package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDispatcher
{
  private List<OrderHandler> handlerList;
  private Map<String, OrderHandler> orderHandlerMap;

  public OrderDispatcher(TableManager tableManager)
  {
    handlerList = List.of(
        new TableOrderHandler(tableManager)
    );

    orderHandlerMap = new HashMap<>();
    for (OrderHandler h : handlerList) {
      orderHandlerMap.put(h.getType(), h);
    }
  }

  public void dispatch(Order order)
  {
    OrderHandler orderHandler = orderHandlerMap.get(order.getOrderType());

    if(orderHandler != null)
    {
      orderHandler.handle(order);
    }
    else
    {
      throw new IllegalArgumentException("No handler for: " + order.getOrderType());
    }
  }
}
