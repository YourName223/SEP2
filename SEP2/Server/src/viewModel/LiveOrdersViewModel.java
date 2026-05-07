package viewModel;

import javafx.application.Platform;
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

  public void clickOrder(OrderCurrent order) {
    model.clickOnOrder(order);
  }

  public void removeOrder(OrderCurrent order)
  {
    model.removeOrder(order);
  }

  public void loadFromModel()
  {
    System.out.println("Test3");
    orderCurrents.clear();
    System.out.println("Test4");
    orderCurrents.addAll(model.getOrdersCurrent());
    System.out.println("Test5");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      loadFromModel();
    });
  }
}