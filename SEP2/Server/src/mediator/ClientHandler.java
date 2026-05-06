package mediator;

import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

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
    {String clientText = null;
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

      OrderPackage orderPackage;
      try
      {
        switch(parser.fromJson(clientText, OrderPackage.class).getType())
        {
          case "order":
            orderPackage = parser.fromJson(clientText, OrderPackage.class);
            if(handlePackage(orderPackage))
            {
              OrderPackage sendPackage = new OrderPackage("Order",null,"Order accepted");
              out.println(parser.toJson(sendPackage));
            }
            else
            {
              OrderPackage sendPackage = new OrderPackage("Order",null,"Order was not accepted");
              out.println(parser.toJson(sendPackage));
            }
            break;
          case "menu":
            model.getMenuItems();
            MenuPackage sendPackage = new MenuPackage("menu",model.getMenuItemsDto());
            out.println(parser.toJson(sendPackage));
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

  public void sendMessage(String message)
  {
    String e = parser.toJson(message);
    out.println(message);
  }

  public boolean handlePackage(OrderPackage orderPackage)
  {
    OrderDto orderDto = new OrderDto(orderPackage.getItems());
    Order order = model.convertOrderDtoToOrder(orderDto);

    model.receiveTableOrder(order,socket.getInetAddress().getHostAddress());
    return true;
  }
}
