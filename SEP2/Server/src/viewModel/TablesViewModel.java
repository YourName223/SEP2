package viewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

public class TablesViewModel
{
  private final Model model;

  private final ObservableList<TableRow> tables = FXCollections.observableArrayList();

  public TablesViewModel(Model model)
  {
    this.model = model;
  }

  public void loadFromModel()
  {
    tables.clear();

    for (String tableNr : model.getAllTableNr())
    {
      tables.add(new TableRow(
          tableNr,
          model.getPriceFromTable(tableNr)
      ));
    }
  }

  public ObservableList<TableRow> getTables()
  {
    return tables;
  }

  public void clear()
  {
    loadFromModel();
  }
}