package model;

import javafx.scene.layout.VBox;
import view.OrderCardRenderer;

public class OrderStateFinished extends OrderState
{
  @Override public void click(OrderCurrent order)
  {
    order.destroy();
  }

  @Override
  public void render(OrderCurrent order,
      VBox container,
      OrderCardRenderer renderer) {

    renderer.renderFinished(order, container);
  }
}
