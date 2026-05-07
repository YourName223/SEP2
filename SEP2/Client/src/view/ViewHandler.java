package view;

import javafx.fxml.FXMLLoader;
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
  private MenuViewController menuViewController;

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
        root = loadMenuView("MenuView.fxml");
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

  private Region loadMenuView(String fxmlFile)
  {
    if (menuViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));
        Region root = loader.load();
        menuViewController = loader.getController();
        menuViewController
            .init(this, viewModelFactory.getMenuListViewModel(), root);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      menuViewController.reset();
    }
    return menuViewController.getRoot();
  }

  public void openPopup(String fxml) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
      Region root = loader.load();

      OrderOverviewViewController controller = loader.getController();

      controller.init(this, viewModelFactory.getOrderContentsViewModel(), root);

      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Order Overview");

      stage.initModality(Modality.APPLICATION_MODAL);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
