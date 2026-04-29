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

import mediator.Server;
import model.Component;
import model.Model;
import model.ModelManager;

public class Main {
  public static void main(String[] args) {
    Model model = new ModelManager();

    Server server = new Server(model);
    Thread serverThread = new Thread(server);
    serverThread.setDaemon(false);
    serverThread.start();

    Component burger = model.createComponent("Burger");
    Component burgerBolle = model.createProduct("BurgerBolle");
    Component bøf = model.createProduct("Bøf");
    model.addProductToComponent(burgerBolle,burger);
    model.addProductToComponent(burgerBolle,bøf);

    Component pomfritter = model.createProduct("Pomfritter");

    System.out.println(burger.getName());
    model.addMenuItem(1,"God Burger","Bøf",2.3);
    model.addProductToMenuItem(0,burger);
    model.addProductToMenuItem(0,pomfritter);

    System.out.println(model.menuItemToString(0));

    System.out.println("Server kører på port 2910...");
  }
}