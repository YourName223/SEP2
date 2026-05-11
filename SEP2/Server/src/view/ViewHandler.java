package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

import java.util.HashMap;
import java.util.Map;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;

  private final Map<String, Object> controllers = new HashMap<>();

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("liveOrders");
  }

  // MAIN ENTRY POINT (no parameters yet)
  public void openView(String id)
  {
    openView(id, null);
  }

  // OVERLOAD with parameter support
  public void openView(String id, Object data)
  {
    Region root = null;

    switch (id)
    {
      case "liveOrders":
        root = loadView("LiveOrdersView.fxml", id, data);
        break;

      case "tables":
        root = loadView("TablesView.fxml", id, data);
        break;

      case "tableOrders":
        root = loadView("TableOrdersView.fxml", id, data);
        break;
    }

    if (root != null)
    {
      currentScene.setRoot(root);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    }
  }

  private Region loadView(String fxml, String id, Object data)
  {
    try
    {
      if (!controllers.containsKey(id))
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));

        Region root = loader.load();
        Object controller = loader.getController();

        controllers.put(id, controller);

        if (id.equals("liveOrders"))
        {
          ((LiveOrdersViewController) controller)
              .init(this, viewModelFactory.getLiveOrdersViewModel(), root);
        }

        if (id.equals("tables"))
        {
          ((TablesViewController) controller)
              .init(this, viewModelFactory.getTablesViewModel(), root);
        }

        if (id.equals("tableOrders"))
        {
          TableOrdersViewController c =
              (TableOrdersViewController) controller;

          c.init(this, viewModelFactory.getTableOrdersViewModel(), root);

          if (data instanceof String)
          {
            viewModelFactory.getTableOrdersViewModel()
                .setSelectedTableNr((String) data);
          }
        }

        return root;
      }
      else
      {
        Object controller = controllers.get(id);

        if (id.equals("tableOrders"))
        {
          TableOrdersViewController c =
              (TableOrdersViewController) controller;

          c.reset();
        }
      }

      return ((Region) ((javafx.scene.Node) primaryStage.getScene().getRoot()));
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }
}