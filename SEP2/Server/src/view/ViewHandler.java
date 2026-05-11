package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.TableOrdersViewModel;
import viewModel.ViewModelFactory;

import java.util.HashMap;
import java.util.Map;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private final ViewModelFactory viewModelFactory;

  private final Map<String, Object> controllers = new HashMap<>();

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    this.currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("liveOrders");
  }

  // -------------------------
  // NAVIGATION API
  // -------------------------

  public void openView(String id)
  {
    Region root = loadView(id);

    if (root != null)
    {
      currentScene.setRoot(root);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    }
  }

  public void openTableOrders(String tableNr)
  {
    TableOrdersViewModel vm = viewModelFactory.getTableOrdersViewModel();

    vm.setSelectedTableNr(tableNr);

    openView("tableOrders");
  }


  private Region loadView(String id)
  {
    try
    {
      if (!controllers.containsKey(id))
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(getFxml(id)));

        Region root = loader.load();
        Object controller = loader.getController();

        controllers.put(id, controller);

        switch (id)
        {
          case "liveOrders":
            ((LiveOrdersViewController) controller)
                .init(this, viewModelFactory.getLiveOrdersViewModel(), root);
            break;

          case "tables":
            ((TablesViewController) controller)
                .init(this, viewModelFactory.getTablesViewModel(), root);
            break;

          case "tableOrders":
            ((TableOrdersViewController) controller)
                .init(this, viewModelFactory.getTableOrdersViewModel(), root);
            break;
        }

        return root;
      }

      return (Region) ((javafx.scene.Node) primaryStage.getScene().getRoot());
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }

  private String getFxml(String id)
  {
    return switch (id)
    {
      case "liveOrders" -> "LiveOrdersView.fxml";
      case "tables" -> "TablesView.fxml";
      case "tableOrders" -> "TableOrdersView.fxml";
      default -> throw new IllegalArgumentException("Unknown view: " + id);
    };
  }
}