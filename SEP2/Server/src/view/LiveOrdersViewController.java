package view;

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

  private final List<OrderCurrent> ordersCurrent = new ArrayList<>();

  private ViewHandler viewHandler;
  private LiveOrdersViewModel viewModel;
  private Region root;

  public LiveOrdersViewController()
  {
  }

  public void reset() {
    refresh();
  }

  public Region getRoot()
  {
    return root;
  }


  @FXML public void initialize()
  {

    incomingRenderer = new IncomingOrderRenderer(this);

    currentRenderer = new CurrentOrderRenderer(this);

    finishedRenderer = new FinishedOrderRenderer(this);
  }

  public void refresh()
  {

    incomingOrderBox.getChildren().clear();
    currentOrderBox.getChildren().clear();
    finishedOrderBox.getChildren().clear();

    for (OrderCurrent o : ordersCurrent)
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

  public void addOrder(Order order)
  {
    ordersCurrent.add(new OrderCurrent(order));
    refresh();
  }

  public void makeButton(OrderCurrent order)
  {
    order.click();
    refresh();
  }

  public void doneButton(OrderCurrent order)
  {
    order.click();
    refresh();
  }

  public void takeButton(OrderCurrent order)
  {
    order.destroy();
    ordersCurrent.remove(order);
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