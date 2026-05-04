package model;

import utility.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void createOrder();
  public void addMenuItemToOrder(MenuItem menuItem,int amount);
  public void setMenuItemOnOrder(MenuItem menuItem, int amount);
  public void removeMenuItemFromOrder(MenuItem menuItem);
  public void placeOrder();
  public void fireProperty(String propertyName, String line);

  public void changeMenu(ArrayList<MenuItemDto> menu);
  public void getMenuFromDataBase();

  public ArrayList<MenuItem> getMenu();
  public Order getOrder();
}
