package view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.*;

public class CurrentOrderRenderer implements OrderCardRenderer {

  private final LiveOrdersViewController controller;
  private final RecipeManager recipeManager;

  public CurrentOrderRenderer(LiveOrdersViewController controller, RecipeManager recipeManager) {
    this.controller = controller;
    this.recipeManager = recipeManager;
  }

  @Override
  public void render(OrderCurrent orderCurrent, VBox container) {

    VBox card = createCard();

    Order order = orderCurrent.getOrder();

    for (OrderItem item : order.getOrderItems()) {

      card.getChildren().add(
          createLabel(item.getItem().getName())
      );

      for (Recipe recipe : item.getItem().getRecipes()) {

        if (recipe == null) continue;

        card.getChildren().add(
            createLabel("  " + recipe.getName())
        );

        for (Ingredient ing : recipe.getIngredients()) {

          card.getChildren().add(
              createIngredientLabel("    - " + ing.getName())
          );
        }
      }
    }

    Button btn = createButton("DONE");
    btn.setOnAction(e -> controller.doneButton(orderCurrent));

    card.getChildren().add(btn);

    container.getChildren().add(card);
  }
}