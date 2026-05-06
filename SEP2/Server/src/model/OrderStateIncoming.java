package model;

public class OrderStateIncoming extends OrderState
{
  @Override public void click(OrderCurrent order)
  {
    order.setState(new OrderStateCurrent());
  }
}
