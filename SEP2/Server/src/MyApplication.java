import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewModel.ViewModelFactory;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    Model model = new ModelManager();
    try
    {
      ViewModelFactory viewModelFactory = ViewModelFactory.getInstance();
      ViewHandler view = new ViewHandler(viewModelFactory);
      view.start(primaryStage);
    }
    catch (Exception e)
    {

    }
  }
}
