package model;

import java.util.List;

public class OrderDto
{
  public List<OrderItemDto> items;

  public OrderDto(List<OrderItemDto> items)
  {
    this.items = items;
  }
}
