package model;

public class ModelManager implements Model
{
  private OrderManager orderManager;
  private OrderDispatcher orderDispatcher;

  public ModelManager()
  {
    orderManager = new OrderManager();
    orderDispatcher = new OrderDispatcher(orderManager);
  }

  @Override public void receiveOrder(Order order)
  {
    orderDispatcher.dispatch(order);
  }
}
