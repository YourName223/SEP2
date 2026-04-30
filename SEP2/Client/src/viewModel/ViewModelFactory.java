package viewModel;

import model.Model;

public class ViewModelFactory
{
  private MenuListViewModel menuListViewModel;

  public ViewModelFactory(Model model)
  {
    menuListViewModel = new MenuListViewModel(model);
  }

  public MenuListViewModel getMenuListViewModel()
  {
    return menuListViewModel;
  }
}
