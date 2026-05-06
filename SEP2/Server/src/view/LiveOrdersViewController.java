package view;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.Order;
import java.util.List;
import model.OrderCurrent;
import model.OrderState;
import model.RecipeManager;

public class LiveOrdersViewController
{
  @FXML VBox incomingOrderBox;
  @FXML VBox currentOrderBox;
  @FXML VBox finishedOrderBox;

  private List<OrderCurrent> ordersCurrent = new java.util.ArrayList<>();
  private final RecipeManager recipeManager;


  private OrderCardRenderer incomingOrderRenderer = new IncomingOrderRenderer(this);
  private OrderCardRenderer currentOrderRenderer = new CurrentOrderRenderer(this, recipeManager);
  private OrderCardRenderer finishedOrderRenderer = new FinishedOrderRenderer(this);

  public void refresh() {
    incomingOrderBox.getChildren().clear();
    currentOrderBox.getChildren().clear();
    finishedOrderBox.getChildren().clear();

    for (OrderCurrent o : ordersCurrent) {

      switch (o.getState()) {

        case INCOMING -> incomingOrderRenderer.render(o, incomingOrderBox);

        case CURRENT -> currentOrderRenderer.render(o, currentOrderBox);

        case FINISHED -> finishedOrderRenderer.render(o, finishedOrderBox);
      }
    }
  }

  public void addOrder(Order order) {

    OrderCurrent liveOrder = new OrderCurrent(order);
    ordersCurrent.add(liveOrder);

    //refresh();
  }

  public void makeButton(OrderCurrent order) {
    order.setState(OrderState.CURRENT);

    //refresh();
  }

  public void doneButton(OrderCurrent order) {
    order.setState(OrderState.FINISHED);
    // refresh();
  }

  public void takeButton(OrderCurrent order) {
    ordersCurrent.remove(order);
    // refresh();
  }
}
