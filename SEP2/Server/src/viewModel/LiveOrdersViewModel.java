package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LiveOrdersViewModel implements PropertyChangeListener
{
  private ObservableList<OrderCurrent> orderCurrents = FXCollections.observableArrayList();
  private final Model model;

  public LiveOrdersViewModel(Model model)
  {
    this.model = model;
    loadFromModel();
    model.addListener("Update",this);
  }

  public ObservableList<OrderCurrent> getOrders() {
    return orderCurrents;
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

  public void loadFromModel()
  {
    orderCurrents.clear();
    orderCurrents.addAll(model.getOrdersCurrent());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    loadFromModel();
  }
}