package model;

import java.util.ArrayList;

public class ModelManager implements Model
{
  private OrderManager orderManager;
  private OrderDispatcher orderDispatcher;
  private MenuManager menuManager;

  public ModelManager()
  {
    menuManager = new MenuManager();
    orderManager = new OrderManager(menuManager);
    orderDispatcher = new OrderDispatcher(orderManager);
  }

  @Override public Order convertOrderDtoToOrder(OrderDto orderDto)
  {
    return orderManager.convertOrderDtoToOrder(orderDto);
  }

  @Override public ArrayList<MenuItemDto> getMenuItemsDto()
  {
    return menuManager.getMenuItemsDto();
  }

  @Override public void receiveTableOrder(Order order, String tableNr)
  {
    TableOrder tableOrder = orderManager.createTableOrder(order,tableNr);
    orderManager.addOrder(tableOrder);
    orderDispatcher.dispatch(tableOrder);
  }

  @Override public ArrayList<MenuItem> getMenuItems()
  {
    return menuManager.getMenuItems();
  }
/*
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

  public void addMenuItem(String name, String allergies, double price)
  {
    menuManager.addMenuItem(name,allergies,price);
  }

  @Override public void addProductToMenuItem(int index, Component product)
  {
    menuManager.addProductToMenuItem(index,product);
  }

  @Override public String menuItemToString(int index)
  {
    return menuManager.menuItemToString(index);
  }
  can be useful later*/
}
