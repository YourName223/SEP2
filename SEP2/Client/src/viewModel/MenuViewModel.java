package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuViewModel implements PropertyChangeListener
{
  private Model model;

  public MenuViewModel(Model model)
  {
    this.model = model;

  }

  public void clear()
  {
    loadFromModel();
  }

  public void loadFromModel()
  {

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}
