package model;

public class Order
{
  private String content;

  public Order()
  {
    new OrderPrinter().printOrder(this);
  }

  public String getContent()
  {
    return content;
  }
}
