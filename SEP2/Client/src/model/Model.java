package model;

import utility.UnnamedPropertyChangeSubject;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void createOrder();
  public void addToOrder(MenuItemDto menuItem,int amount);
  public void updateOrderItem(MenuItemDto menuItem,int amount);
  public void removeFromOrder(MenuItemDto menuItem);
  public void placeOrder();
  public void fireProperty(String propertyName, String line);

  public void changeMenu(ArrayList<MenuItemDto> menu);
  public void getMenuFromDataBase();

  public ArrayList<MenuItemDto> getMenu();
  public Order getOrder();
}
