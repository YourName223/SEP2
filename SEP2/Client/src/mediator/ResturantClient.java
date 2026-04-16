package mediator;

import com.google.gson.Gson;
import model.Model;
import model.Order;
import model.OrderManager;
import parser.XmlJsonParser;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ResturantClient
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;
  private XmlJsonParser parser;
  private boolean waiting;
  private Model model;
  private OrderPackage orderPackage;
  private ResturantClientReader resturantClientReader;

  public ResturantClient(Model model, String host, int port)
  {
    try
    {
      this.model = model;
      socket = new Socket(host, port);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      gson = new Gson();
      waiting = false;
      resturantClientReader = new ResturantClientReader(this, in);
      new Thread(resturantClientReader).start();
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
    Order order = parseOrder(line);

    if (order != null)
    {
      model.addOrder(order);
    }
  }

  public void sendOrder(OrderPackage orderPackage)
  {
    String message = gson.toJson(orderPackage);
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
