package view;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.*;

import java.text.spi.DateFormatSymbolsProvider;
import java.util.ArrayList;
import java.util.List;

public class LiveOrdersViewController {

  @FXML VBox incomingOrderBox;
  @FXML VBox currentOrderBox;
  @FXML VBox finishedOrderBox;

  private IncomingOrderRenderer incomingRenderer;
  private CurrentOrderRenderer currentRenderer;
  private FinishedOrderRenderer finishedRenderer;

  private final RecipeManager recipeManager;

  private final List<OrderCurrent> ordersCurrent = new ArrayList<>();

  private OrderCardRenderer renderer;

  public LiveOrdersViewController(RecipeManager recipeManager) {
    this.recipeManager = recipeManager;
  }

  @FXML
  public void initialize() {

    incomingRenderer = new IncomingOrderRenderer(this);

    currentRenderer = new CurrentOrderRenderer(
        this,
        recipeManager
    );

    finishedRenderer = new FinishedOrderRenderer(this);
  }

  public void refresh() {

    incomingOrderBox.getChildren().clear();
    currentOrderBox.getChildren().clear();
    finishedOrderBox.getChildren().clear();

    for (OrderCurrent o : ordersCurrent) {

      VBox container;

      if (o.getState() instanceof OrderStateCurrent) {
        container = currentOrderBox;
      }
      else if (o.getState() instanceof OrderStateFinished) {
        container = finishedOrderBox;
      }
      else {
        container = incomingOrderBox;
      }

      o.getState().render(o, container, renderer);
    }
  }

  public void addOrder(Order order) {
    ordersCurrent.add(new OrderCurrent(order));
    refresh();
  }

  public void makeButton(OrderCurrent order) {
    order.click();
    refresh();
  }

  public void doneButton(OrderCurrent order) {
    order.click();
    refresh();
  }

  public void takeButton(OrderCurrent order) {
    order.destroy();
    ordersCurrent.remove(order);
    refresh();
  }

  public VBox getIncomingOrderBox() { return incomingOrderBox; }
  public VBox getCurrentOrderBox() { return currentOrderBox; }
  public VBox getFinishedOrderBox() { return finishedOrderBox; }
}