package model;

public class OrderStateCurrent extends OrderState
{
  @Override public void click(OrderCurrent order)
  {
    order.setState(new OrderStateFinished());
  }
}
