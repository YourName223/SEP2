package model;

import java.util.ArrayList;

public class ModelManager implements Model
{
  private OrderManager orderManager;
  private OrderDispatcher orderDispatcher;
  private MenuManager menuManager;

  public ModelManager()
  {
    orderManager = new OrderManager();
    orderDispatcher = new OrderDispatcher(orderManager);
    menuManager = new MenuManager();
  }

  @Override public void receiveOrder(Order order)
  {
    orderDispatcher.dispatch(order);
  }

  @Override public ArrayList<MenuItem> getMenuItems()
  {
    return menuManager.getMenuItems();
  }

  @Override public Component createComponent(String name)
  {
    return new Composite(name);
  }

  @Override public Product createProduct(String name)
  {
    return new Product(name);
  }

  @Override public Component addProductToComponent(Component product,
      Component component)
  {
    component.add(product);
    return component;
  }

  public void addMenuItem(int id, String name, String allergies, double price)
  {
    menuManager.addMenuItem(id,name,allergies,price);
  }

  @Override public void addProductToMenuItem(int index, Component product)
  {
    menuManager.addProductToMenuItem(index,product);
  }

  @Override public String menuItemToString(int index)
  {
    return menuManager.menuItemToString(index);
  }
}
