package model;

import javafx.scene.layout.VBox;
import view.OrderCardRenderer;

public class OrderStateIncoming extends OrderState
{
  @Override public void click(OrderCurrent order)
  {
    order.setState(new OrderStateCurrent());
  }

  @Override
  public void render(OrderCurrent order,
      VBox container,
      OrderCardRenderer renderer) {

    renderer.renderIncoming(order, container);
  }
}
