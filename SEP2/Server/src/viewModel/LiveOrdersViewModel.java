package viewModel;

import javafx.collections.ObservableList;
import model.*;

public class LiveOrdersViewModel {

  private final Model model;

  public LiveOrdersViewModel(Model model) {
    this.model = model;
  }

  public ObservableList<OrderCurrent> getOrders() {
    return model.getOrders();
  }

  public RecipeManager getRecipeManager() {
    return model.getRecipeManager();
  }

  public void addOrder(Order order) {
    model.addOrder(order);
  }

  public void clickOrder(OrderCurrent order) {
    order.click();
  }
}