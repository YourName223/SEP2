package model;

public class Order
{
  private String content;

  public Order(String content)
  {
    this.content = content;
    new OrderPrinter().printOrder(this);
  }

  public String getContent()
  {
    return content;
  }
}
