package mediator;

import model.Model;
import model.OrderManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server
{
  private Model model;
  private int PORT = 2910;
  private boolean running;
  private ServerSocket welcomeSocket;
  private List<ClientHandler> clients;
  private OrderManager orderManager;

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
    catch (IOException e) {e.printStackTrace();}
    System.out.println("Server stopped.");
  }

  public synchronized void broadcast(String message)
  {
    for (ClientHandler client : clients)
    {
      client.sendMessage(message);
    }
  }
}
