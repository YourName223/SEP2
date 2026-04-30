package viewModel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OrderContentsViewModel implements PropertyChangeListener
{
  private Model model;
  private StringProperty successProperty;
  private StringProperty errorProperty;

  public OrderContentsViewModel(Model model)
  {
    this.model = model;
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

}
