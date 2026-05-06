package mediator;

import com.google.gson.Gson;
import model.MenuItemDto;
import model.Model;
import parser.ParserException;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson parser;
  private boolean waiting;
  private Model model;
  private OrderPackage orderPackage;
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

  public void received(String line) throws ParserException
  {
    System.out.println(parser.fromJson(line, OrderPackage.class).getType());
    switch(parser.fromJson(line, OrderPackage.class).getType())
    {
      case "Order":
        orderPackage = parser.fromJson(line, OrderPackage.class);
        model.fireProperty("Update",orderPackage.getMessage());
        break;
      case "menu":
        MenuPackage menuPackage = parser.fromJson(line, MenuPackage.class);
        System.out.println(menuPackage.getMenuItems());
        model.changeMenu(menuPackage.getMenuItems());
        for(MenuItemDto menuItemDto : menuPackage.getMenuItems())
        {
          System.out.println(menuItemDto.getName());
        }
        break;
    }
  }

  public void sendOrder(OrderPackage orderPackage)
  {
    String message = parser.toJson(orderPackage);
    out.println(message);
  }

  public void getMenu()
  {
    MenuPackage menuPackage = new MenuPackage("menu",null);
    String message = parser.toJson(menuPackage);
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
