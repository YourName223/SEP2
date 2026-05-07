package viewModel;

import model.Model;

public class ViewModelFactory {

  private final LiveOrdersViewModel liveOrdersViewModel;

  public ViewModelFactory(Model model) {
    liveOrdersViewModel = new LiveOrdersViewModel(model);
  }

  public LiveOrdersViewModel getLiveOrdersViewModel() {
    return liveOrdersViewModel;
  }
}