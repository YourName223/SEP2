package model;

public class OrderManager
{
  private Order order;

  public OrderManager()
  {
    order = new Order(null);
  }

  public Order createOrder()
  {
    return new Order("An order");
  }

  public void addProductToOrder(MenuItem menuItem)
  {
    order.addProduct(menuItem);
  }

  public Order getOrder()
  {
    return order;
  }
}
