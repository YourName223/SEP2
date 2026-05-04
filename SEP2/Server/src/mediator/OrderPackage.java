package mediator;

import model.Order;
import model.OrderItemDto;

import java.util.List;

public class OrderPackage extends BasePackage
{
  private String txt;

  private List<OrderItemDto> items;

  public OrderPackage(String type, List<OrderItemDto> items, String txt)
  {
    super(type);
    this.items = items;
    this.txt = txt;
  }

  public List<OrderItemDto> getItems()
  {
    return items;
  }

  public String getTxt()
  {
    return txt;
  }
}