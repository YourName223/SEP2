package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

public class LiveOrdersViewModel {

  private final OrderManager orderManager;
  private final RecipeManager recipeManager;
  private final ObservableList<OrderCurrent> orders =
      FXCollections.observableArrayList();

  public LiveOrdersViewModel(OrderManager orderManager, RecipeManager recipeManager) {
    this.orderManager = orderManager;
    this.recipeManager = recipeManager;
  }

  public ObservableList<OrderCurrent> getOrders() {
    return orders;
  }

  public RecipeManager getRecipeManager() {
    return recipeManager;
  }

  public void addOrder(Order order) {
    orderManager.addOrder(order);
    OrderCurrent oc = new OrderCurrent(order);
    orders.add(oc);
  }

  public void clickOrder(OrderCurrent order) {
    order.click();
    if (order.shouldRemove()) {
      orders.remove(order);
    } else {
      int idx = orders.indexOf(order);
      if (idx >= 0) {
        orders.set(idx, order);
      }
    }
  }
}