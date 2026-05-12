package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import java.beans.PropertyChangeEvent;

public class TablesViewModel
{
  private final Model model;

  private final ObservableList<TableRowViewModel> tables =
      FXCollections.observableArrayList();

  public TablesViewModel(Model model)
  {
    this.model = model;

    model.addListener("Update", this::onModelUpdate);
  }

  private void onModelUpdate(PropertyChangeEvent evt)
  {
    loadFromModel();
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

  private Runnable openTableCallback;

  public void setOpenTableCallback(Runnable r)
  {
    this.openTableCallback = r;
  }

  public void openTable(String tableNr)
  {
    if (openTableCallback != null)
      openTableCallback.run();
  }

  public void clear()
  {
    loadFromModel();
  }
}