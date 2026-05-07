package model;

import javafx.scene.layout.VBox;
import view.OrderCardRenderer;

public class OrderStateCurrent extends OrderState
{
  @Override public void click(OrderCurrent order)
  {
    order.setState(new OrderStateFinished());
  }

}
