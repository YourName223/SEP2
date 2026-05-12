import javafx.application.Application;
import javafx.stage.Stage;
import model.MenuItem;
import model.Model;
import model.Order;
import model.Recipe;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.util.ArrayList;

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
      /*for (int i = 0; i<1;i++)
      {
        order.addMenuItem(model.getMenuItems().get(0));
      }*/

      ArrayList<Recipe> recipes = new ArrayList<>();

      recipes.add(new Recipe("2","Sodavand"));

      for (int i = 0; i<11; i++)
      {
        order.addMenuItem(new MenuItem(",",",",2,recipes));
      }


      model.receiveTableOrder(order,"23");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
