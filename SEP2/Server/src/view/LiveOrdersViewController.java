package view;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.Order;

public class LiveOrdersViewController
{
  @FXML VBox newOrdersBox;
  @FXML VBox currentOrdersBox;
  @FXML VBox readyOrdersBox;

  private List<LiveOrder> liveOrders;

  private OrderCardRenderer incomingOrderRenderer = new IncomingOrderRenderer();
  private OrderCardRenderer currentOrderRenderer = new CurrentOrderRenderer();
  private OrderCardRenderer finishedOrderRenderer = new FinishedOrderRenderer();

  public void refresh() {
    newOrdersBox.getChildren().clear();
    currentOrdersBox.getChildren().clear();
    readyOrdersBox.getChildren().clear();

    for (LiveOrder o : liveOrders) {

      switch (o.getState()) {

        case INCOMING -> incomingOrderRenderer.render(o, newOrdersBox);

        case CURRENT -> currentOrderRenderer.render(o, currentOrdersBox);

        case FINISHED -> finishedOrderRenderer.render(o, readyOrdersBox);
      }
    }
  }

  public void addOrder(Order order) {

    LiveOrder liveOrder = new LiveOrder(order);
    liveOrders.add(liveOrder);

    //refresh();
  }

  public void makeButton(LiveOrder order) {
    order.setState(OrderState.CURRENT);

    //refresh();
  }

  public void doneButton(LiveOrder order) {
    order.setState(OrderState.FINISHED);
    // refresh();
  }

  public void takeButton(LiveOrder order) {
    liveOrders.remove(order);
    // refresh();
  }
}
