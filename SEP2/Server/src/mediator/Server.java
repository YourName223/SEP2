package mediator;

import com.google.gson.Gson;
import model.Model;
import model.OrderItemDto;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable, PropertyChangeListener
{
  private Model model;
  private static int PORT = 2910;
  private boolean running;
  private ServerSocket welcomeSocket;
  private Gson parser;
  private List<ClientHandler> clients;

  public Server(Model model)
  {
    this.model = model;
    this.clients = new ArrayList<>();
    parser = new Gson();

    model.addListener("Broadcast",this);
    model.addListener("RemoveOrder",this);
    model.addListener("RemoveAllOrders",this);
    model.addListener("StartTimer",this);
    model.addListener("StopTimer",this);
    model.getMenuItems();
    MenuPackage sendPackage = new MenuPackage("Menu",model.getMenuItemsDto());
    System.out.println(parser.toJson(sendPackage));
  }

  public void close()
  {
    running = false;
    try
    {
      welcomeSocket.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    System.out.println("Server stopped.");
  }

  public synchronized void broadcast(String message)
  {
    for (ClientHandler client : clients)
    {
      client.sendMessage(message);
    }
  }

  @Override public void run()
  {
    running = true;

    try
    {
      welcomeSocket = new ServerSocket(PORT);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }

    while (running)
    {
      ClientHandler c = null;
      try
      {
        c = new ClientHandler(welcomeSocket.accept(), model);
        clients.add(c);
        Thread t1 = new Thread(c);
        t1.setDaemon(true);
        t1.start();
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }
  }

  public void removeAllOrdersFromClient(String ip)
  {
    for(ClientHandler client : clients)
    {
      if(client.getIp().equals(ip))
      {
        OrderPackage sentPackage = new OrderPackage("Order",null,"RemoveAll");
        client.sendMessage(parser.toJson(sentPackage));
        break;
      }
    }
  }

  public void sendClientOrderMessage(String ip, ArrayList<OrderItemDto> orderItemDtos, String message)
  {
    for(ClientHandler client : clients)
    {
      if(client.getIp().equals(ip))
      {
        OrderPackage sentPackage = new OrderPackage("Order",orderItemDtos,message);
        client.sendMessage(parser.toJson(sentPackage));
        break;
      }
    }
  }

  public void sendClientOrderMessage(String ip, int id, String message)
  {
    for(ClientHandler client : clients)
    {
      if(client.getIp().equals(ip))
      {
        OrderPackage sentPackage = new OrderPackage("Order",null,message);
        sentPackage.setOrderId(id);
        client.sendMessage(parser.toJson(sentPackage));
        break;
      }
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "RemoveOrder":
        System.out.println(evt.getPropertyName().toString());
        sendClientOrderMessage(evt.getNewValue().toString(),(ArrayList<OrderItemDto>)evt.getOldValue(),evt.getPropertyName());
        break;
      case "StartTimer", "StopTimer":
        sendClientOrderMessage(evt.getNewValue().toString(),(int)evt.getOldValue(),evt.getPropertyName());
        break;
      case "RemoveAllOrders":
        removeAllOrdersFromClient(evt.getNewValue().toString());
        break;
      case "Broadcast":
        broadcast(evt.getNewValue().toString());
        break;
    }
  }
}
