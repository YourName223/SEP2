package viewModel;

import model.Model;

public class ViewModelFactory
{
  private final LiveOrdersViewModel liveOrdersViewModel;
  private final TablesViewModel tablesViewModel;
  private final TableOrdersViewModel tableOrdersViewModel;
  private final InventoryViewModel inventoryViewModel;

  public ViewModelFactory(Model model)
  {
    this.liveOrdersViewModel = new LiveOrdersViewModel(model);
    this.tablesViewModel = new TablesViewModel(model);
    this.tableOrdersViewModel = new TableOrdersViewModel(model);
    this.inventoryViewModel = new InventoryViewModel(model);
  }

  public LiveOrdersViewModel getLiveOrdersViewModel()
  {
    return liveOrdersViewModel;
  }

  public TablesViewModel getTablesViewModel()
  {
    return tablesViewModel;
  }

  public TableOrdersViewModel getTableOrdersViewModel()
  {
    return tableOrdersViewModel;
  }

  public InventoryViewModel getInventoryViewModel()
  {
    return inventoryViewModel;
  }
}