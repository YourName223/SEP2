package viewModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MenuItem;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuListViewModel implements PropertyChangeListener
{
  private Model model;
  private MenuItem menuItem;
  private final ObservableList<MenuViewModel> menuItems = FXCollections.observableArrayList();
  private IntegerProperty amount;

  public MenuListViewModel(Model model)
  {
    this.model = model;
    menuItem = null;
    amount = new SimpleIntegerProperty();
    loadFromModel();
  }

  public void clear()
  {
    loadFromModel();
  }

  public IntegerProperty getAmount()
  {
    return amount;
  }

  public void increase()
  {
    amount.set(amount.get()+1);
  }

  public void decrease()
  {
    amount.set(amount.get()-1);
  }

  public void setSelectedMenuItem(MenuItem menuItem)
  {
    this.menuItem = menuItem;
    amount.set(0);
  }

  public void addToOrder()
  {
    System.out.println("Tried to add to order");
    for(int i = 0; i < amount.get(); i++)
    {
      model.addProductToOrder(menuItem);
    }
  }

  public ObservableList<MenuViewModel> getMenuItems()
  {
    return menuItems;
  }

  public MenuItem getMenuItem()
  {
    return menuItem;
  }

  public void loadFromModel()
  {
    System.out.println("Test");
    for(MenuItem menuItem : model.getMenu())
    {
      System.out.println("Test1");
      menuItems.add(new MenuViewModel(model,menuItem));
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }
}
