package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest
{
  Order order;
  OrderSender orderSender;
  OrderManager orderManager;
  Model model;

  @BeforeEach void setUp()
  {
    model = new ModelManager();
    orderSender = new OrderSender(model);
    orderManager = new OrderManager();
    order = null;
  }

  @Test void createOrderZero()
  {
    assertEquals(null,order);
  }

  @Test void createOrderOne()
  {
    order = orderManager.createOrder();

    assertEquals("An order",order.getContent());
  }

  @Test void placeOrderZero()
  {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> orderSender.placeOrder(null)
    );

    assertEquals("Arguments cannot be null", e.getMessage());
  }

  @Test void placeOrderOne()
  {
    assertDoesNotThrow(() -> orderSender.placeOrder(new Order()));
  }
}