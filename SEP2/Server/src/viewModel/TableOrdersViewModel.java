package viewModel;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import model.OrderItem;

public class TableOrdersViewModel
{
  private Model model;
  private StringProperty successProperty;
  private StringProperty errorProperty;
  private DoubleProperty total;
  private StringProperty tableNumber;

  public TableOrdersViewModel(Model model)
  {
    total = new SimpleDoubleProperty();
    this.model = model;
    this.successProperty = new SimpleStringProperty();
    this.errorProperty = new SimpleStringProperty();

    //model.addListener("Update",this);
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
    //table number
    //total
  }

  public StringProperty getSuccessProperty()
  {
    return successProperty;
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }


  public ObservableValue<String> getTotal()
  {
  }

  public ObservableValue<String> getTableNumber()
  {
  }

  public void backToTables()
  {
  }

  public void resetOrders()
  {
  }
}
