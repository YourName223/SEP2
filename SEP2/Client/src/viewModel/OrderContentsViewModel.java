package viewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MenuItem;
import model.Model;
import model.OrderItem;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrderContentsViewModel implements PropertyChangeListener
{
  private Model model;
  private OrderItem orderItem;
  private final ObservableList<OrderItemViewModel> orderItems = FXCollections.observableArrayList();
  private StringProperty successProperty;
  private StringProperty errorProperty;


  public OrderContentsViewModel(Model model)
  {
    this.model = model;
    orderItem = null;
    this.successProperty = new SimpleStringProperty();
    this.errorProperty = new SimpleStringProperty();

    model.addListener("Update",this);
    loadFromModel();
  }

  public void clear()
  {
    loadFromModel();
  }

  public void loadFromModel()
  {
    successProperty.set("");
    errorProperty.set("");
  }

  public StringProperty getSuccessProperty()
  {
    return successProperty;
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals("Update"))
    {
      Platform.runLater( () -> successProperty.set(evt.getNewValue().toString()));
    }
  }

  public ObservableList<OrderItemViewModel> getOrderItems()
  {
    return orderItems;
  }

  public void placeOrder()
  {
    model.placeOrder();
  }

  public void setSelectedOrderItem(OrderItem orderItem)
  {
    this.orderItem = orderItem;
  }
}
