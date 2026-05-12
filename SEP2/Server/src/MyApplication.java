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
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler view = new ViewHandler(viewModelFactory);
      view.start(primaryStage);

      Order order = new Order();
      order.addMenuItem(model.getMenuItems().get(0));

      model.receiveTableOrder(order,"23");
      System.out.println(model.getPriceFromTable("23"));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
