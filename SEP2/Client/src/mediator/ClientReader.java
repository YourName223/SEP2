package mediator;

import java.io.BufferedReader;

public class ClientReader implements Runnable
{
  private BufferedReader in;
  private boolean running;
  private Client client;

  public ClientReader(Client client, BufferedReader in)
  {
    this.client = client;
    this.in = in;
    running = true;
  }

  @Override public void run()
  {
    try
    {
      while (running)
      {
        String line = in.readLine();

        if (line == null)
          break;

        client.received(line);
      }
    }
    catch (Exception e)
    {
      if (running)
        e.printStackTrace();
    }
  }

  public void close()
  {
    running = false;

    try
    {
      in.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}