package model;

public class OrderStateFinished extends OrderState
{
  @Override public void click(OrderCurrent order)
  {
    order.destroy();
  }
}
