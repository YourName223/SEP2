package model;

public class OrderCurrent
{
  private Order order;
  private OrderState orderState;
  private boolean remove;

  public OrderCurrent(Order order)
  {
    this.order = order;
    orderState = new OrderStateIncoming();
    remove = false;
  }

  public void click()
  {
    orderState.click(this);
  }

  public void setState(OrderState state)
  {
    orderState = state;
  }

  public void destroy()
  {
    remove = true;
  }

  public boolean shouldRemove()
  {
    return remove;
  }

  public Order getOrder()
  {
    return order;
  }

  public String getState()
  {
    return orderState.status();
  }
}
