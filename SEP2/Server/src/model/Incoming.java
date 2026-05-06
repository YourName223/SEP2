package model;

public class Incoming extends OrderState
{
  @Override public void click(OrderCurrent order)
  {
    order.setState(new Current());
  }
}
