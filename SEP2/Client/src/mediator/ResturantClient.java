package mediator;

import com.google.gson.Gson;
import model.Model;
import model.Order;
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
  private boolean running;
  private OrderManager orderManager;
  private Gson gson;
  private XmlJsonParser parser;
  private Model model;
  private ResturantClientReader resturantClientReader;

  public ResturantClient(Socket socket, Model model)
  {
    try
    {
      this.model = model;
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      gson = new Gson();
      running = true;
      resturantClientReader = new ResturantClientReader(this, in);
      new Thread(new ResturantClientReader(this, in)).start();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void close()
  {
    running = false;
    resturantClientReader.close();
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
    String e = gson.toJson(orderPackage);
    out.println(message);
  }

  private Order parseOrder(String line)
  {
    OrderPackage packageObj = gson.fromJson(line, OrderPackage.class);
    return packageObj.getOrder();
  }

  public void received(String line)
  {
    Order order = parseOrder(line);

    if (order != null)
    {
      model.addOrder(order);
    }
  }
}
