package viewModel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MenuItem;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuViewModel implements PropertyChangeListener
{
  private Model model;
  private MenuItem menuItem;

  public MenuViewModel(Model model)
  {
    this.model = model;

  }

  public void clear()
  {
    loadFromModel();
  }

  public void increase()
  {
    model.addProductToOrder(menuItem);
  }

  public void decrease()
  {
    model.removeProductFromOrder(menuItem);
  }

  public void setSelectedMenuItem(MenuItem menuItem)
  {
    this.menuItem = menuItem;
  }

  public ObservableList<MenuItem> getMenuItems()
  {
    ObservableList<MenuItem> list = FXCollections.observableArrayList();
    list.addAll(model.getMenu());
    return list;
  }

  public void loadFromModel()
  {

  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}
