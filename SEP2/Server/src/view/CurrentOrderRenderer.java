package view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.*;

public class CurrentOrderRenderer implements OrderCardRenderer {

  private final LiveOrdersViewController controller;

  public CurrentOrderRenderer(LiveOrdersViewController controller) {
    this.controller = controller;
  }

  @Override
  public void render(LiveOrder liveOrder, VBox container) {

    VBox card = createCard();

    Order order = liveOrder.getOrder();

    for (OrderItem item : order.getItems()) {

      card.getChildren().add(
          createLabel(item.getItem().getName())
      );

      for (String recipeId : item.getItem().getRecipeIds()) {

        Recipe recipe = getRecipeById(recipeId);

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
    btn.setOnAction(e -> controller.doneButton(liveOrder));

    card.getChildren().add(btn);

    container.getChildren().add(card);
  }

  private Recipe getRecipeById(String recipeId) {
    // placeholder — should be moved to service layer later
    return null;
  }
}