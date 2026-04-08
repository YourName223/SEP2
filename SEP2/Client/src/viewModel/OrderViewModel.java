package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Order;
import model.Table;

public class OrderViewModel
{
  private Model model;
  private StringProperty successProperty;

  public OrderViewModel(Model model)
  {
    this.model = model;
    this.successProperty = new SimpleStringProperty();
  }

  public void clear()
  {
    successProperty.set("");
  }

  public StringProperty getSuccessProperty()
  {
    return successProperty;
  }

  public void order()
  {
    if(model.placeOrder(new Table(),new Order()))
    {
      successProperty.set("Order was placed");
    }
  }
}
