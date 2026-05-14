package model;

import utility.UnnamedPropertyChangeSubject;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void createOrder();
  public void addToOrder(MenuItemDto menuItem,int amount);
  public void updateOrderItem(MenuItemDto menuItem,int amount);
  public void removeFromOrder(MenuItemDto menuItem);
  public void removeOrder();
  public void removeAllOrders();
  public void placeOrder();
  public void orderFeedback(String message);
  public void changeMenu(ArrayList<MenuItemDto> menu);
  public void getMenuFromDataBase();
  public void updateTime(ArrayList<OrderItem> orderItems, ArrayList<String> time);
  public ArrayList<MenuItemDto> getMenu();
  public Order getOrder();
  public ArrayList<Order> getOldOrders();
  public void cancelOrder(ArrayList<OrderItemDto> orderItemDto);
}
