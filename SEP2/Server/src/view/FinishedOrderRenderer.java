package view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.*;

public class FinishedOrderRenderer implements OrderCardRenderer {

  private final LiveOrdersViewController controller;

  public FinishedOrderRenderer(LiveOrdersViewController controller) {
    this.controller = controller;
  }

  @Override
  public void render(OrderCurrent liveOrder, VBox container) {

    VBox card = createCard();

    Order order = liveOrder.getOrder();

    // Table number
    card.getChildren().add(
        createLabel("Table: " + order.getTableNumber())
    );

    // Items
    for (OrderItem item : order.getOrderItems()) {
      card.getChildren().add(
          createLabel(item.getItem().getName())
      );
    }

    // TAKE button
    Button btn = createButton("TAKE");
    btn.setOnAction(e -> controller.takeButton(liveOrder));

    card.getChildren().add(btn);

    container.getChildren().add(card);
  }
}