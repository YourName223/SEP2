package viewModel;

import model.MenuManager;
import model.OrderManager;
import model.RecipeManager;

public class ViewModelFactory {

  private static ViewModelFactory instance;

  private final MenuManager menuManager;
  private final OrderManager orderManager;
  private final RecipeManager recipeManager;
  private final LiveOrdersViewModel liveOrdersViewModel;

  private ViewModelFactory() {
    try {
      menuManager = new MenuManager();
      recipeManager = new RecipeManager();
      orderManager = new OrderManager(menuManager);
      liveOrdersViewModel = new LiveOrdersViewModel(orderManager, recipeManager);    } catch (Exception e) {
      throw new RuntimeException("Kunne ikke initialisere ViewModelFactory", e);
    }
  }

  public static ViewModelFactory getInstance() {
    if (instance == null) {
      instance = new ViewModelFactory();
    }
    return instance;
  }

  public LiveOrdersViewModel getLiveOrdersViewModel() {
    return liveOrdersViewModel;
  }

  public MenuManager getMenuManager() {
    return menuManager;
  }

  public RecipeManager getRecipeManager() {
    return recipeManager;
  }
}