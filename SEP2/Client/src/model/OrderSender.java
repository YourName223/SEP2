package model;

public class OrderSender
{
  public void placeOrder(Order order)
  {
    if(order == null)
    {
      throw new IllegalArgumentException("Arguments cannot be null");
    }

    if(false)//Sends order to server side
    {

    }
    else
    {
      throw new IllegalStateException("Order could not be placed");
    }
  }
}
