package model;

public abstract class OrderState
{
    public abstract void click(OrderCurrent order);

    public String status()
    {
      return getClass().getSimpleName();
    }
}
