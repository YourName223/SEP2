package viewModel;

import model.Model;

public class ViewModelFactory
{
  private MenuViewModel menuViewModel;

  public ViewModelFactory(Model model)
  {
    menuViewModel = new MenuViewModel(model);
  }

  public MenuViewModel getMenuViewModel()
  {
    return menuViewModel;
  }
}
