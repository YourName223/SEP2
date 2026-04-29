package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;

import java.io.IOException;

public class ViewHandler
{
  private Stage primaryStage;
  private Scene currentScene;
  private ViewModelFactory viewModelFactory;
  private OrderViewController orderViewController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("menu");
  }

  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "menu":
        root = loadOrderView("MenuView.fxml");
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
            .init(this, viewModelFactory.getOrderViewModel(), root);
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

  public void openPopup(String fxml) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
      Parent root = loader.load();

      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Popup");

      stage.initModality(Modality.APPLICATION_MODAL); // optional but recommended
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
