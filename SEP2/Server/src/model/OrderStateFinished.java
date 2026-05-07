package model;

import javafx.scene.layout.VBox;
import view.OrderCardRenderer;

public class OrderStateFinished extends OrderState
{
  @Override public void click(OrderCurrent order)
  {
    order.destroy();
  }
}
