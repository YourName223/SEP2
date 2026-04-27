package model;

public class Order
{
  private String content;
  private String orderType;

  public Order(String content)
  {
    this.content = content;
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
