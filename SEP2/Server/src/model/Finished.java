package model;

public class Finished extends OrderState
{
  @Override public void click(OrderCurrent order)
  {
    order.destroy();
  }
}
