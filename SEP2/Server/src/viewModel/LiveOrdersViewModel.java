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

  public ObservableList<OrderCurrent> getOrders()
  {
    return orderCurrents;
  }

  public void clickOrder(OrderCurrent order)
  {
    model.clickOnOrder(order);
  }

  public void removeOrder(OrderCurrent order)
  {
    model.removeOrder(order);
  }

  public void loadFromModel()
  {
    orderCurrents.clear();
    orderCurrents.addAll(model.getOrdersCurrent());
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> loadFromModel());
  }
}