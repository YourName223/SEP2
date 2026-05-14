package mediator;

import com.google.gson.Gson;
import model.Model;
import model.Order;
import model.OrderItemDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson parser;
  private boolean waiting;
  private Model model;
  private ClientReader clientReader;

  public Client(Model model, String host, int port)
  {
    try
    {
      this.model = model;
      socket = new Socket(host, port);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      parser = new Gson();
      waiting = false;
      clientReader = new ClientReader(this, in);
      new Thread(clientReader).start();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void disconnect()
  {
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

  public void received(String line)
  {
    switch(parser.fromJson(line, OrderPackage.class).getType())
    {
      case "Order":
        OrderPackage orderPackage = parser.fromJson(line, OrderPackage.class);
        switch (orderPackage.getMessage())
        {
          case "Remove":
            model.removeOrder();
            break;
          case "RemoveAll":
            model.removeAllOrders();
            break;
          case "Cancel":
            model.removeOrder(orderPackage.getItems());
            break;
          case "Order accepted":
            model.acceptOrder();
        }
        model.orderFeedback(orderPackage.getMessage());
        break;
      case "Menu":
        System.out.println("Got menu");
        MenuPackage menuPackage = parser.fromJson(line, MenuPackage.class);
        model.changeMenu(menuPackage.getMenuItems());
        break;
    }
  }

  public void sendOrder(ArrayList<OrderItemDto> orderItemDto)
  {
    String message = parser.toJson(new OrderPackage("Order",orderItemDto,"Send"));
    out.println(message);
  }

  public void getMenu()
  {
    MenuPackage menuPackage = new MenuPackage("Menu",null);
    String message = parser.toJson(menuPackage);
    out.println(message);
  }

  public void cancelOrder(ArrayList<OrderItemDto> orderItemDto)
  {
    OrderPackage orderPackage = new OrderPackage("Order",orderItemDto,"Cancel");
    String message = parser.toJson(orderPackage);
    out.println(message);
  }

  private synchronized void waitingForReply()
  {
    waiting = true;

    while (waiting)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
