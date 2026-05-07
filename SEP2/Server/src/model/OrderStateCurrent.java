package model;

import javafx.scene.layout.VBox;
import view.OrderCardRenderer;

public class OrderStateCurrent extends OrderState
{

  @Override public void click(OrderCurrent order)
  {
    order.setState(new OrderStateFinished());
  }

  @Override
  public void render(OrderCurrent order,
      VBox container,
      OrderCardRenderer renderer) {

    renderer.renderCurrent(order, container);
  }
}
