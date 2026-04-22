package mediator;

import model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;
import model.Order;

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
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }

      OrderPackage orderPackage;
      String reply = "";
      try
      {
        orderPackage = parser.fromJson(clientText, OrderPackage.class);
        handlePackage(orderPackage);

        reply = "Order accepted";
      }
      catch (Exception e)
      { e.printStackTrace();}}
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
