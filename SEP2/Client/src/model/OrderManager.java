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

  public void addProductToOrder(MenuItem menuItem, int amount)
  {
    order.addProduct(menuItem,amount);
  }

  public void setProductToOrder(MenuItem menuItem, int amount)
  {
    order.setProduct(menuItem,amount);
  }

  public void removeProductFromOrder(MenuItem menuItem)
  {
    order.removeProduct(menuItem);
  }

  public Order getOrder()
  {
    return order;
  }
}
