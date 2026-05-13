package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TablesViewModel implements PropertyChangeListener
{
  private final Model model;

  private final ObservableList<TableRowViewModel> tables =
      FXCollections.observableArrayList();

  public TablesViewModel(Model model)
  {
    this.model = model;

    model.addListener("Update",this);
  }

  public void loadFromModel()
  {
    tables.clear();

    for (String tableNr : model.getAllTableNr())
    {
      tables.add(new TableRowViewModel(
          tableNr,
          model.getPriceFromTable(tableNr)
      ));
    }
  }

  public ObservableList<TableRowViewModel> getTables()
  {
    return tables;
  }

  public void clear()
  {
    loadFromModel();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    loadFromModel();
  }
}