package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model
{
  private PropertyChangeSupport property;
  private OrderManager orderManager;
  private OrderDispatcher orderDispatcher;
  private MenuManager menuManager;
  private RecipeManager recipeManager;
  private TableManager tableManager;
  private IngredientManager ingredientManager;

  public ModelManager()
  {
    menuManager = new MenuManager();
    tableManager = new TableManager();
    orderManager = new OrderManager(menuManager);
    orderDispatcher = new OrderDispatcher(tableManager);
    recipeManager = new RecipeManager();
    ingredientManager = new IngredientManager();
    property = new PropertyChangeSupport(this);
  }

  @Override public Order convertOrderDtoToOrder(OrderDto orderDto)
  {
    return orderManager.convertOrderDtoToOrder(orderDto);
  }

  @Override public ArrayList<MenuItemDto> getMenuItemsDto()
  {
    ArrayList<MenuItemDto> menuItemDtos = new ArrayList<>();
    for (MenuItem menuItem : menuManager.getMenuItems())
    {
      menuItemDtos.add(
          new MenuItemDto(
              menuItem.getName(),
              menuItem.getAllergies(),
              menuItem.getPrice(),
              recipeManager.getRecipeIdFromMenuItem(menuItem),
              ingredientManager.amountOfStockForMenuItem(menuItem),
              menuItem.getPrepTime()));
    }
    return menuItemDtos;
  }

  @Override public void clickOnOrder(OrderCurrent order)
  {
    orderManager.clickOnOrder(order);
    property.firePropertyChange("Update",null,null);

    if(order.getOrder().getOrderType().equals("Table"))
    {
      property.firePropertyChange("Order time updated",null,(((TableOrder)order.getOrder()).getTableNr()));
    }
  }

  @Override public void removeOrder(OrderCurrent order)
  {
    orderManager.removeOrder(order);
    tableManager.removeOrder(order.getOrder());
    ingredientManager.addRecipeIngredientsFromOrder(order.getOrder());

    if(order.getOrder().getOrderType().equals("Table"))
    {
      property.firePropertyChange("RemoveOrder",orderManager.convertOrderToOrderItemDto(order.getOrder()),(((TableOrder)order.getOrder()).getTableNr()));
    }
    property.firePropertyChange("Update",null,null);
  }

  @Override public boolean receiveTableOrder(Order order, String tableNr)
  {
    if(ingredientManager.hasStockForOrder(order))
    {
      TableOrder tableOrder = orderManager.createTableOrder(order, tableNr);
      orderManager.addOrder(tableOrder);
      orderDispatcher.dispatch(tableOrder);
      ingredientManager.removeRecipeIngredientsFromOrder(order);
      property.firePropertyChange("Update", null, null);
      return true;
    }
    return false;
  }

  @Override public ArrayList<MenuItem> getMenuItems()
  {
    return menuManager.getMenuItems();
  }

  @Override public ArrayList<String> getAllTableNr()
  {
    return tableManager.getAllTableNr();
  }

  @Override public ArrayList<OrderCurrent> getOrdersCurrent()
  {
    return orderManager.getOrderList().getOrders();
  }

  @Override public ArrayList<Order> getOrdersFromTable(String tableNr)
  {
    return tableManager.getOrdersFromTable(tableNr);
  }

  @Override public void removeAllOrdersFromTable(String tableNr)
  {
    property.firePropertyChange("RemoveAllOrders",null,(tableNr));
    tableManager.removeAllOrdersFromTable(tableNr);
  }

  @Override public double getPriceFromTable(String tableNr)
  {
    return tableManager.getPriceFromTable(tableNr);
  }

  @Override public void broadCast(String message)
  {
    property.firePropertyChange("Broadcast",null,message);
  }

  @Override public ArrayList<Ingredient> getIngredients()
  {
    return ingredientManager.getStock();
  }

  @Override public void setStockOnIngredient(String id,
      double stock)
  {
    ingredientManager.setStock(id, stock);
  }

  @Override public boolean cancelOrder(OrderItemDto orderItemDto)
  {
    for (OrderCurrent order1 : orderManager.getOrderList().getOrders())
    {
      for (OrderItem orderItem1 : order1.getOrder().getOrderItems())
      {
        OrderItem orderItem = new OrderItem(menuManager.getMenuItemById(orderItemDto.getMenuItemId()));
        orderItem.setQuantity(orderItemDto.getQuantity());
        if (orderItem1.equals(orderItem))
        {
          if(order1.getState() instanceof OrderStateIncoming)
          {
            orderManager.removeOrderItem(order1.getOrder(),orderItem);
            tableManager.removeOrderItem(order1.getOrder(),orderItem);
            ingredientManager.addRecipeIngredientsFromMenuItem(orderItem.getItem());
            property.firePropertyChange("Update",null,null);
            return true;
          }
          return false;
        }
      }
    }
    return false;
  }

  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(propertyName,listener);
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
