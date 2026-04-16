package mediator;

import model.Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;
import model.Model;
import model.Order;
import model.OrderManager;
import parser.XmlJsonParser;

public class ClientHandler
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private OrderManager orderManager;
  private Gson parser;
  private Model model;
  private ResturantClientReader resturantClientReader;

  public ClientHandler(Socket socket, Model model)
  {
    try
    {
      this.model = model;
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      parser = new Gson();
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
    String e = parser.toJson(message);
    out.println(message);
  }

  public void handlePackage(OrderPackage orderPackage)
  {
    Order order = parseOrder(orderPackage.toString());

    if (order != null)
    {
      model.recieveOrder(socket.getInetAddress().getHostAddress() ,order);
    }
  }

  private Order parseOrder(String line)
  {
    OrderPackage packageObj;
    packageObj = parser.fromJson(line, OrderPackage.class);
    return (packageObj.getOrder());
  }
}
