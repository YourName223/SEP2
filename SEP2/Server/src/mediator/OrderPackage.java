package mediator;

import model.Order;

public class OrderPackage extends BasePackage
{
  private String txt;

  private Order order;

  public OrderPackage(String type, Order order, String txt)
  {
    super(type);
    this.type = type;
    this.order = order;
    this.txt = txt;
  }

  public Order getOrder()
  {
    return order;
  }

  public String getTxt()
  {
    return txt;
  }
}
