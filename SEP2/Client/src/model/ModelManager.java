package model;

public class ModelManager implements Model
{
  @Override public boolean placeOrder(Table table, Order order)
  {
    if(table == null || order == null)
    {
      throw new IllegalArgumentException("Arguments cannot be null");
    }

    if(false)//Sends order to server side
    {
      return true;
    }
    else
    {
      throw new IllegalStateException("Order could not be placed");
    }
  }
}
