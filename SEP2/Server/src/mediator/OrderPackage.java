package mediator;

import model.OrderItemDto;

import java.util.ArrayList;

public class OrderPackage extends BasePackage
{
  private String message;

  private ArrayList<OrderItemDto> items;

  public OrderPackage(String type, ArrayList<OrderItemDto> items, String message)
  {
    super(type);
    this.items = items;
    this.message = message;
  }

  public ArrayList<OrderItemDto> getItems()
  {
    return items;
  }

  public String getMessage()
  {
    return message;
  }
}