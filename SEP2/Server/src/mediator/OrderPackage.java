package mediator;

import model.Order;

public class OrderPackage
{
  private Order order;

  public OrderPackage(Order order)
  {
    this.order = order;
  }

  public Order getOrder()
  {
    return order;
  }
}
