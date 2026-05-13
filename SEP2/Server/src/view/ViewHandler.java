package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;

  private final ViewModelFactory viewModelFactory;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    this.currentScene = new Scene(new javafx.scene.layout.Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openTabs();
  }

  private <T> Parent load(String fxml, T viewModel)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
      Parent root = loader.load();

      Object controller = loader.getController();


      if (controller instanceof ViewController<?> vc)
      {
        @SuppressWarnings("unchecked")
        ViewController<T> typedController = (ViewController<T>) vc;

        typedController.init(viewModel);
      }

      return root;
    }
    catch (Exception e)
    {
      throw new RuntimeException("Failed to load: " + fxml, e);
    }
  }


  private void setScene(Parent root)
  {
    currentScene.setRoot(root);
    primaryStage.setScene(currentScene);
    primaryStage.show();
  }


  public void openTabs()
  {
    Parent root = load("TabsView.fxml", viewModelFactory);

    setScene(root);
  }
}