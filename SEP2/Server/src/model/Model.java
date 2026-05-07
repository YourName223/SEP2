package model;

import javafx.collections.ObservableList;
import utility.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  public Order convertOrderDtoToOrder(OrderDto orderDto);

  public ArrayList<MenuItemDto> getMenuItemsDto();

  public void clickOnOrder(OrderCurrent order);

  public void receiveTableOrder(Order order, String tableNr);

  public ArrayList<MenuItem> getMenuItems();

  ArrayList<OrderCurrent> getOrdersCurrent();

  RecipeManager getRecipeManager();
/*
  public Component createComponent(String name);

  public Product createProduct(String name);

  public Component addProductToComponent(Component product, Component component);

  public void addProductToMenuItem(int index, Component product);

  public void addMenuItem(String name, String allergies, double price);

  public String menuItemToString(int index);
  can be useful later*/
}
