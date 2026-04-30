package mediator;

import model.MenuItem;
import model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;
import model.Order;
import model.TableOrder;

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
      OrderPackage sendOrderPackage = null;
      MenuPackage menuPackage = null;
      try
      {
        System.out.println("Client asked for something");
        switch(parser.fromJson(clientText, OrderPackage.class).getType())
        {
          case "order":
            orderPackage = parser.fromJson(clientText, OrderPackage.class);
            if(handlePackage(orderPackage))
            {
              sendOrderPackage = new OrderPackage("Order",null,"Order accepted");
            }
            else
            {
              sendOrderPackage = new OrderPackage("Order",null,"Order was not accepted");
            }
            break;
          case "menu":
            System.out.println("Client asked for menu");
            menuPackage = new MenuPackage("menu",model.getMenuItems());

            break;
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      try
      {
        if(sendOrderPackage.getTxt() != null)
        {
          out.println(sendOrderPackage);
        }
      }
      catch (Exception e)
      {

      }
      try
      {
        if(menuPackage.getType() != null)
        {
          out.println(menuPackage);
        }
      }
      catch (Exception e)
      {

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
    Order order = orderPackage.getOrder();

    if (order != null)
    {
      model.receiveOrder(new TableOrder(order,socket.getInetAddress().getHostAddress()));
      return true;
    }
    return false;
  }

  private Order parseOrder(String line)
  {
    OrderPackage packageObj;
    packageObj = parser.fromJson(line, OrderPackage.class);
    return (packageObj.getOrder());
  }
}
