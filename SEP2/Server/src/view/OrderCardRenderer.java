package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.OrderCurrent;

public interface OrderCardRenderer {

  void render(OrderCurrent order, VBox container);

  default VBox createCard() {
    VBox card = new VBox();

    card.setStyle(
        "-fx-background-color: #2e2e2e;" +
            "-fx-padding: 10;" +
            "-fx-background-radius: 8;"
    );

    card.setSpacing(5);

    return card;
  }

  default Label createLabel(String text) {
    Label label = new Label(text);
    label.setStyle("-fx-font-size: 14px;");
    return label;
  }

  default Label createIngredientLabel(String text) {
    Label label = new Label(text);
    label.setStyle(
        "-fx-font-size: 14px;" +
            "-fx-text-fill: #888888;"
    );
    return label;
  }

  default Button createButton(String text) {
    Button btn = new Button(text);
    btn.setStyle("-fx-font-size: 18px;");
    return btn;
  }
}