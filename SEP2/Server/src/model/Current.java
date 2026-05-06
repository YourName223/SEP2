package model;

public class Current extends OrderState
{

  @Override public void click(OrderCurrent order)
  {
    order.setState(new Finished());
  }
}
