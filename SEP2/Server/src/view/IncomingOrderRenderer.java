package view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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

    Button btnMake = createButton("MAKE");
    btnMake.setOnAction(e -> controller.makeButton(liveOrder));

    Button btnDelete = createButton("X");
    btnDelete.setOnAction(e -> controller.deleteButton(liveOrder));

    Region space = new Region();
    HBox.setHgrow(space, Priority.ALWAYS);

    HBox doOrDont = new HBox(btnMake, space, btnDelete);

    card.getChildren().add(doOrDont);

    container.getChildren().add(card);
  }
}