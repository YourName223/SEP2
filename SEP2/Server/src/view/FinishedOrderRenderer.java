package view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.*;

public class FinishedOrderRenderer implements OrderCardRenderer
{
  private final LiveOrdersViewController controller;

  public FinishedOrderRenderer(LiveOrdersViewController controller)
  {
    this.controller = controller;
  }

  @Override
  public void render(OrderCurrent liveOrder, VBox container)
  {
    VBox card = createCard();
    Order order = liveOrder.getOrder();

    if (order instanceof TableOrder)
    {
      TableOrder tableOrder = (TableOrder) order;

      card.getChildren().add(createLabel( tableOrder.getTableNr()));
    }
    else
    {
      card.getChildren().add(createLabel("Not for here"));
    }

    for (OrderItem item : order.getOrderItems())
    {
      card.getChildren().add(
          createLabel(item.getQuantity() + "x " +
              item.getItem().getName())
      );
    }

    Button btn = createButton("TAKE");
    btn.setOnAction(e -> controller.takeButton(liveOrder));

    card.getChildren().add(btn);

    container.getChildren().add(card);
  }
}