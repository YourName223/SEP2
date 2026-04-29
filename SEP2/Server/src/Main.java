/*//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
  public static void main(String[] args)
  {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    System.out.printf("Hello and welcome!");

    for (int i = 1; i <= 5; i++)
    {
      //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
      // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
      System.out.println("i = " + i);
    }
  }
}*/

import Repository.MenuDAO;
import Repository.MenuDAOImpl;
import mediator.Server;
import model.Component;
import model.Model;
import model.ModelManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws SQLException
  {
    Model model = new ModelManager();

    Server server = new Server(model);
    Thread serverThread = new Thread(server);
    serverThread.setDaemon(false);
    serverThread.start();
    MenuDAO menuDAO = new MenuDAOImpl();

    ArrayList<String> names = menuDAO.getAllNames();

    System.out.println("Names:"+names);
    System.out.println(menuDAO.readByName(names.getFirst()));

    System.out.println("Server kører på port 2910...");
  }
}