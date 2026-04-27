package model;

public class ModelManager implements Model
{
  private OrderManager orderManager;
  private OrderDispatcher orderDispatcher;
  private Menu menu;

  public ModelManager()
  {
    orderManager = new OrderManager();
    orderDispatcher = new OrderDispatcher(orderManager);
    menu = new Menu();
  }

  @Override public void receiveOrder(Order order)
  {
    orderDispatcher.dispatch(order);
  }

  @Override public Menu getMenu()
  {
    return menu;
  }
}
