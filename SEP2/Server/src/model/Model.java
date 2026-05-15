package model;

import utility.UnnamedPropertyChangeSubject;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  public Order convertOrderDtoToOrder(OrderDto orderDto);
  public ArrayList<MenuItemDto> getMenuItemsDto();
  public void clickOnOrder(OrderCurrent order);
  public void removeOrder(OrderCurrent order);
  public boolean receiveTableOrder(Order order, String tableNr);
  public ArrayList<MenuItem> getMenuItems();
  public ArrayList<String> getAllTableNr();
  ArrayList<OrderCurrent> getOrdersCurrent();
  public ArrayList<Order> getOrdersFromTable(String tableNr);
  public void removeAllOrdersFromTable(String tableNr);
  public double getPriceFromTable(String tableNr);
  public int getNewOrderId();
  public void broadCast(String message);
  public ArrayList<Ingredient> getIngredients();
  public void setStockOnIngredient(String id, double stock);
  public boolean cancelOrder(OrderItemDto orderItemDto);
/*
  public Component createComponent(String name);

  public Product createProduct(String name);

  public Component addProductToComponent(Component product, Component component);

  public void addProductToMenuItem(int index, Component product);

  public void addMenuItem(String name, String allergies, double price);

  public String menuItemToString(int index);
  can be useful later*/
}
