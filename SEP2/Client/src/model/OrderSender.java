package model;

import mediator.OrderPackage;
import mediator.Client;

public class OrderSender
{
  Client client;

  public OrderSender(Model model)
  {
    client = new Client(model,"10.154.208.86",2910);
  }

  public void placeOrder(Order order)
  {
    if(order == null)
    {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    else
    {
      client.sendOrder(new OrderPackage(order));

      if(true)//Sends order to server side
      {

      }
      else
      {
        throw new IllegalStateException("Order could not be placed");
      }
    }
  }
}
