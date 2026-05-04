package model;

import java.util.ArrayList;

public class OrderManager
{
  private ArrayList<Order> orders;
  private MenuManager menuManager;
  private RecipeManager recipeManager;

  public OrderManager(MenuManager menuManager)
  {
    this.menuManager = menuManager;
    orders = new ArrayList<>();
  }

  public TableOrder createTableOrder(Order order, String tableNr)
  {
    return new TableOrder(order,tableNr);
  }

  public void addOrder(Order order)
  {
    orders.add(order);
    new OrderPrinter().printOrder(order);
  }

  public Order convertOrderDtoToOrder(OrderDto orderDto)
  {
    Order order = new Order("An order");

    ArrayList<OrderItem> items = new ArrayList<>();

    for(OrderItemDto orderItemDto : orderDto.items)
    {
      Recipe recipe = recipeManager.getRecipe(orderItemDto.getRecipeId());

      MenuItem menuItem = menuManager.getMenuItemById(recipe.getId());

      OrderItem orderItem = new OrderItem(menuItem);
      orderItem.setQuantity(orderItemDto.getQuantity());

      items.add(orderItem);
    }

    order.setProducts(items);

    return order;
  }
}
