package mediator;

import model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.Gson;

public class ClientHandler implements Runnable
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private Gson parser;
  private Model model;

  public ClientHandler(Socket socket, Model model)
  {
    try
    {
      this.socket = socket;
      this.model = model;
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      parser = new Gson();
      running = true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    running = true;

    while (running)
    {
      String clientText = null;
      try
      {
        clientText = in.readLine();
        if (clientText == null)
        {
          close();
          break;
        }
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }

      try
      {
        switch(parser.fromJson(clientText, OrderPackage.class).getType())
        {
          case "Order":
            OrderPackage orderPackage = parser.fromJson(clientText, OrderPackage.class);
            handleOrderPackage(orderPackage);
            break;
          case "Menu":
            handleMenuPackage();
            break;
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }

  public void close()
  {
    running = false;
    try
    {
      if (socket != null)
        socket.close();

      if (in != null)
        in.close();

      if (out != null)
        out.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public String getIp()
  {
    return socket.getInetAddress().getHostAddress();
  }

  public void sendMessage(String message)
  {
    out.println(message);
  }

  public Order convertOrderPackageToOrder(OrderPackage orderPackage)
  {
    OrderDto orderDto = new OrderDto(orderPackage.getItems());
    return model.convertOrderDtoToOrder(orderDto);
  }

  public void handleMenuPackage()
  {
    model.getMenuItems();
    MenuPackage sendPackage = new MenuPackage("Menu",model.getMenuItemsDto());
    out.println(parser.toJson(sendPackage));
  }

  public void handleOrderPackage(OrderPackage orderPackage)
  {
    Order order = convertOrderPackageToOrder(orderPackage);
    OrderPackage sendPackage;
    switch (orderPackage.getMessage())
    {
      case "Send":
        if(model.receiveTableOrder(order,socket.getInetAddress().getHostAddress()))
        {
          sendPackage = new OrderPackage("Order",null,"Order accepted");
          sendPackage.setOrderId(model.getNewOrderId());
          model.broadCast(parser.toJson(new MenuPackage("Menu",model.getMenuItemsDto())));
        }
        else
        {
          sendPackage = new OrderPackage("Order",null,"Order was not accepted");
        }
        break;
      case "Cancel":
        if(model.cancelOrder(orderPackage.getItems().getFirst()))
        {
          sendPackage = new OrderPackage("Order",orderPackage.getItems(),"Cancel");
          model.broadCast(parser.toJson(new MenuPackage("Menu",model.getMenuItemsDto())));
        }
        else
        {
          sendPackage = new OrderPackage("Order",orderPackage.getItems(),"Order was not Canceled");
        }
        break;
      default:
        sendPackage = new OrderPackage("Order",null,null);
        break;
    }
    out.println(parser.toJson(sendPackage));
  }
}
