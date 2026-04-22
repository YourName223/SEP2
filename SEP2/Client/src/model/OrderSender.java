package model;

import mediator.OrderPackage;
import mediator.ResturantClient;

public class OrderSender
{
  ResturantClient resturantClient;

  public OrderSender(Model model)
  {
    resturantClient = new ResturantClient(model,"192.168.1.90",2910);
  }

  public void placeOrder(Order order)
  {
    if(order == null)
    {
      throw new IllegalArgumentException("Arguments cannot be null");
    }

    resturantClient.sendOrder(new OrderPackage(order));

    if(false)//Sends order to server side
    {

    }
    else
    {
      throw new IllegalStateException("Order could not be placed");
    }
  }
}
