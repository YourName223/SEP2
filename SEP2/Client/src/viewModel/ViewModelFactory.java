package viewModel;

import model.Model;

public class ViewModelFactory
{
  private MenuListViewModel menuListViewModel;
  private OrderContentsViewModel orderContentsViewModel;

  public ViewModelFactory(Model model)
  {
    menuListViewModel = new MenuListViewModel(model);
    orderContentsViewModel = new OrderContentsViewModel(model);
  }

  public MenuListViewModel getMenuListViewModel()
  {
    return menuListViewModel;
  }

  public OrderContentsViewModel getOrderContentsViewModel()
  {
    return orderContentsViewModel;
  }
}
