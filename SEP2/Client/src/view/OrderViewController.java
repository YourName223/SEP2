package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewModel.OrderViewModel;

public class OrderViewController
{
  @FXML private Label successLabel;

  private ViewHandler viewHandler;
  private OrderViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, OrderViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;

    successLabel.textProperty().bind(viewModel.getSuccessProperty());
  }

  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void orderButton()
  {

  }
}
