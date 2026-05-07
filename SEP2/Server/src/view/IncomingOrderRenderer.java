package view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.OrderCurrent;
import model.Order;
import model.OrderItem;

public class IncomingOrderRenderer implements OrderCardRenderer {

  private final LiveOrdersViewController controller;

  public IncomingOrderRenderer(LiveOrdersViewController controller) {
    this.controller = controller;
  }

  @Override
  public void render(OrderCurrent liveOrder, VBox container) {

    VBox card = createCard();

    Order order = liveOrder.getOrder();

    for (OrderItem item : order.getOrderItems()) {

      card.getChildren().add(
          createLabel(
              item.getQuantity() + "x " +
                  item.getItem().getName()
          )
      );
    }

    Button btn1 = createButton("Delete");
    btn1.setOnAction(e -> controller.deleteButton(liveOrder));

    Button btn = createButton("MAKE");
    btn.setOnAction(e -> controller.makeButton(liveOrder));

    card.getChildren().add(btn);
    card.getChildren().add(btn1);

    container.getChildren().add(card);
  }
}