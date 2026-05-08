package model;

import java.util.ArrayList;

public class OrderDto
{
  public ArrayList<OrderItemDto> items;

  public OrderDto(ArrayList<OrderItemDto> items)
  {
    this.items = items;
  }
}
