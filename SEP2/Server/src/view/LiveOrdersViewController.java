package view;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.*;
import viewModel.LiveOrdersViewModel;

import java.text.spi.DateFormatSymbolsProvider;
import java.util.ArrayList;
import java.util.List;

public class LiveOrdersViewController
{

  @FXML VBox incomingOrderBox;
  @FXML VBox currentOrderBox;
  @FXML VBox finishedOrderBox;

  private IncomingOrderRenderer incomingRenderer;
  private CurrentOrderRenderer currentRenderer;
  private FinishedOrderRenderer finishedRenderer;


  private ViewHandler viewHandler;
  private LiveOrdersViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, LiveOrdersViewModel viewModel,
      Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    incomingRenderer = new IncomingOrderRenderer(this);

    currentRenderer = new CurrentOrderRenderer(this);

    finishedRenderer = new FinishedOrderRenderer(this);

    viewModel.getOrders().addListener((javafx.collections.ListChangeListener<OrderCurrent>) change -> {
      refresh();
    });

  }

  public void reset()
  {
    viewModel.loadFromModel();
    refresh();
  }

  public Region getRoot()
  {
    return root;
  }



  public void refresh()
  {
    incomingOrderBox.getChildren().clear();
    currentOrderBox.getChildren().clear();
    finishedOrderBox.getChildren().clear();

    for (OrderCurrent o : viewModel.getOrders())
    {
      VBox container;
      OrderCardRenderer renderer;

      if (o.getState() instanceof OrderStateCurrent)
      {
        container = currentOrderBox;
        renderer = currentRenderer;
      }
      else if (o.getState() instanceof OrderStateFinished)
      {
        container = finishedOrderBox;
        renderer = finishedRenderer;
      }
      else
      {
        container = incomingOrderBox;
        renderer = incomingRenderer;
      }

      renderer.render(o, container);
    }
  }

  public void render(OrderCurrent order, VBox container,
      OrderCardRenderer renderer)
  {

    renderer.render(order, container);
  }

  public void makeButton(OrderCurrent order)
  {
    viewModel.clickOrder(order);
    refresh();
  }

  public void doneButton(OrderCurrent order)
  {
    viewModel.clickOrder(order);
    refresh();
  }

  public void takeButton(OrderCurrent order)
  {
    viewModel.clickOrder(order);
    refresh();
  }

  public void deleteButton(OrderCurrent order)
  {
    viewModel.removeOrder(order);
    refresh();
  }

  public VBox getIncomingOrderBox()
  {
    return incomingOrderBox;
  }

  public VBox getCurrentOrderBox()
  {
    return currentOrderBox;
  }

  public VBox getFinishedOrderBox()
  {
    return finishedOrderBox;
  }
}