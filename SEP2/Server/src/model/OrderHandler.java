package model;

public interface OrderHandler
{
  public void handle(Order order);

  public String getType();
}
