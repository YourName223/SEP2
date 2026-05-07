package model;

import javafx.scene.layout.VBox;
import view.OrderCardRenderer;

public abstract class OrderState
{
    public abstract void click(OrderCurrent order);

  public abstract void render(OrderCurrent order,
      VBox container,
      OrderCardRenderer renderer);
}
