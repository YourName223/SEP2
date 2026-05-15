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
  private int prepTimeSec;
  private Timeline timeline;

  private final StringProperty waitingTimeProperty = new SimpleStringProperty();

  public OrderItemViewModel(OrderItem item)
  {
    this.item = item;

    this.prepTimeSec = item.getMenuItem().getPrepTimeSec();

    updateText();
    startCountdown();
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

  public StringProperty getWaitingTimeProperty()
  {return waitingTimeProperty;}

  private void startCountdown()
  {
    timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> tick()));

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  private void tick()
  {
    if (prepTimeSec <= 0)
    {
      waitingTimeProperty.set("0:00");
      return;
    }

    prepTimeSec--;
    updateText();
  }

  private void updateText()
  {
    int minutes = prepTimeSec / 60;
    int seconds = prepTimeSec % 60;

    waitingTimeProperty.set(String.format("%d:%02d", minutes, seconds));
  }

  public void stop()
  {
    if (timeline != null)
    {
      timeline.stop();
    }
  }

}
