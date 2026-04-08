import model.ModelManager;
import model.Order;
import model.Table;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
  public static void main(String[] args)
  {
    ModelManager modelManager = new ModelManager();
    modelManager.placeOrder(new Table(),new Order());
  }
}