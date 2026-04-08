package viewModel;

import model.Model;

public class ViewModelFactory
{
  private OrderViewModel orderViewModel;

  public ViewModelFactory(Model model)
  {
    orderViewModel = new OrderViewModel(model);
  }

  public OrderViewModel getOrderViewModel()
  {
    return orderViewModel;
  }
}
