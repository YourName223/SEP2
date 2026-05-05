package mediator;

import model.OrderItemDto;

import java.util.List;

public class OrderPackage extends BasePackage
{
  private String message;

  private List<OrderItemDto> items;

  public OrderPackage(String type, List<OrderItemDto> items, String message)
  {
    super(type);
    this.items = items;
    this.message = message;
  }

  public List<OrderItemDto> getItems()
  {
    return items;
  }

  public String getMessage()
  {
    return message;
  }
}