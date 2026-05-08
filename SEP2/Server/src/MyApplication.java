import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.Order;
import view.ViewHandler;
import viewModel.ViewModelFactory;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    Model model = Main.model;

    try
    {
      System.out.println("test1");
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      System.out.println("test2");
      ViewHandler view = new ViewHandler(viewModelFactory);
      System.out.println("test3");
      view.start(primaryStage);
      System.out.println("test4");

      Order order = new Order();
      order.addMenuItem(model.getMenuItems().get(0));

      model.receiveTableOrder(order,"23");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
