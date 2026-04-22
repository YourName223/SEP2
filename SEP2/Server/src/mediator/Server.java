package mediator;

import model.Model;
import model.OrderManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable
{
  private Model model;
  private static int PORT = 2910;
  private boolean running;
  private ServerSocket welcomeSocket;
  private List<ClientHandler> clients;

  public Server(Model model)
  {
    this.model = model;
    this.clients = new ArrayList<>();
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
}
