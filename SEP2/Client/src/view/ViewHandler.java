package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private OrderViewController orderViewController;

  public ViewHandler()
  {
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("order");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "order":
        root = loadOrderView("OrderView.fxml");
        break;
    }

    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadOrderView(String fxmlFile)
  {
    if (orderViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        orderViewController = loader.getController();
        orderViewController
            .init(this, root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      orderViewController.reset();
    }
    return orderViewController.getRoot();
  }
}
