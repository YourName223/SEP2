package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelManagerTest
{
  Order order;
  OrderManager orderManager;
  Model model;

  @BeforeEach void setUp()
  {
    model = new ModelManager();
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
        () -> model.placeOrder()
    );

    assertEquals("Arguments cannot be null", e.getMessage());
  }

  @Test void placeOrderOne()
  {
    assertDoesNotThrow(() -> model.placeOrder());
  }
}