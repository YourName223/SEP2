package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

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
}
