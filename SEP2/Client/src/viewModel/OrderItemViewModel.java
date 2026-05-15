package viewModel;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.OrderItem;

public class OrderItemViewModel
{
  private OrderItem item;

  private final StringProperty waitingTimeProperty = new SimpleStringProperty();

  public OrderItemViewModel(OrderItem item)
  {
    this.item = item;
    updateText();
  }

  public OrderItem getOrderItem()
  {
    return item;
  }

  public String getName()
  {
    return item.getMenuItem().getName();
  }

  public double getPrice()
  {
    return item.getMenuItem().getPrice();
  }

  public int getQuantity()
  {
    return item.getQuantity();
  }

  public StringProperty waitingTimeProperty()
  {
    return waitingTimeProperty;
  }

  public void start()
  {
    System.out.println("Should start");
    item.setActive(true);
    System.out.println(item.isActive());
  }

  public void tick()
  {
    System.out.println("Should tick" + item.isActive());
    if (item.isActive())
    {
      if (item.getMenuItem().getPrepTimeSec() <= 0)
      {
        waitingTimeProperty.set("0:00");
        item.setActive(false);
      }
      else
      {
        item.getMenuItem().setPrepTimeSec(item.getMenuItem().getPrepTimeSec()-1);
        updateText();
      }
    }
  }

  public void forceZero()
  {
    System.out.println("Force zero");
    item.getMenuItem().setPrepTimeSec(1);
  }

  private void updateText()
  {
    int minutes = item.getMenuItem().getPrepTimeSec() / 60;
    int seconds = item.getMenuItem().getPrepTimeSec() % 60;

    waitingTimeProperty.set(String.format("%d:%02d", minutes, seconds));
  }

  public boolean isActive()
  {
    return item.isActive();
  }
}
