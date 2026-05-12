package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import viewModel.ViewModelFactory;

public class TabsViewController implements ViewController<ViewModelFactory>
{
  @FXML private Tab liveOrdersTab;
  @FXML private Tab tablesTab;
  @FXML private StackPane tablesContainer;

  private ViewModelFactory vmf;

  @Override
  public void init(ViewModelFactory vmf)
  {
    this.vmf = vmf;

    liveOrdersTab.setContent(
        loadView("LiveOrdersView.fxml", vmf.getLiveOrdersViewModel())
    );

    showTablesView();
  }

  public void showTablesView()
  {
    tablesContainer.getChildren().setAll(
        loadView("TablesView.fxml", vmf.getTablesViewModel())
    );
  }

  public void showTableOrdersView(String tableNr)
  {
    var vm = vmf.getTableOrdersViewModel();
    vm.setSelectedTableNr(tableNr);

    tablesContainer.getChildren().setAll(
        loadView("TableOrdersView.fxml", vm)
    );
  }

  private <T> Parent loadView(String fxml, T viewModel)
  {
    try
    {
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource(fxml));

      Parent root = loader.load();
      Object controller = loader.getController();

      if (controller instanceof ViewController<?> vc)
      {
        @SuppressWarnings("unchecked")
        ViewController<T> specificViewController = (ViewController<T>) vc;
        specificViewController.init(viewModel);
      }

      if (controller instanceof TablesViewController tvc)
      {
        tvc.setTabsController(this);
      }

      return root;
    }
    catch (Exception e)
    {
      throw new RuntimeException("Failed to load " + fxml, e);
    }
  }
}