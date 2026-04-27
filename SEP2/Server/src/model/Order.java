package model;

public abstract class Order
{
  private String content;
  private String orderType;

  public Order(String content)
  {
    this.content = content;
    new OrderPrinter().printOrder(this);
  }

  public void setOrderType(String orderType)
  {
    this.orderType = orderType;
  }

  public String getContent()
  {
    return content;
  }

  public String getOrderType()
  {
    return orderType;
  }
}
