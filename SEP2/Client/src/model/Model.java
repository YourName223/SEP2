package model;

import utility.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void createOrder();
  public void addProductToOrder(MenuItem menuItem,int amount);
  public void setProductToOrder(MenuItem menuItem, int amount);
  public void removeProductFromOrder(MenuItem menuItem);
  public void placeOrder();
  public void fireProperty(String propertyName, String line);

  public void changeMenu(ArrayList<MenuItem> menu);
  public void getMenuFromDataBase();

  public ArrayList<MenuItem> getMenu();
  public Order getOrder();
}
