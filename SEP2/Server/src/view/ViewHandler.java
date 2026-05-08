package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;
  private LiveOrdersViewController liveOrdersViewController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    System.out.println("test5");
    openView("live Orders");
    System.out.println("what?");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "live Orders":
        root = loadMenuView("LiveOrdersView.fxml");
        break;
    }

    currentScene.setRoot(root);
    String title = "";

    if (root != null)
    {
      primaryStage.setTitle(title);
      primaryStage.setScene(currentScene);
      primaryStage.setWidth(root.getPrefWidth());
      primaryStage.setHeight(root.getPrefHeight());
      primaryStage.show();
    }
  }

  private Region loadMenuView(String fxmlFile)
  {
    if (liveOrdersViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        liveOrdersViewController = loader.getController();
        liveOrdersViewController
            .init(this, viewModelFactory.getLiveOrdersViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      liveOrdersViewController.reset();
    }

    return liveOrdersViewController.getRoot();
  }
}
