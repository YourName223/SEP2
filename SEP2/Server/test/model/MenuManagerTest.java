import model.MenuItem;
import model.MenuManager;
import model.ModelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MenuManagerTest
{
  ArrayList<MenuItem> menuItems;
  MenuManager menuManager;
  ModelManager modelManager;

  @BeforeEach void setUp()
  {
    menuManager = new MenuManager();
    menuItems = new ArrayList<>();
    modelManager = new ModelManager();
  }

  @Test void addMenuItemZero()
  {
    IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
        () -> menuManager.addMenuItem(0,null,null,0)
    );

    assertEquals("Arguments cannot be null", e.getMessage());
  }

  @Test void addMenuItemOne()
  {
    menuManager.addMenuItem(1,"1","1",1);
    assertEquals(1,menuManager.getMenuItems().size());
  }

  @Test void addMenuItemMany()
  {
    menuManager.addMenuItem(1,"1","1",1);
    menuManager.addMenuItem(2,"1","1",1);
    assertEquals(2,menuManager.getMenuItems().size());
  }

  @Test void getMenuItemZero()
  {
    Exception e = assertThrows(Exception.class,() -> menuManager.getMenuItem(0));

    assertEquals("Index 0 out of bounds for length 0", e.getMessage());
  }


  @Test void getMenuItemsOne()
  {
    addMenuItemOne();
    assertEquals(1,menuManager.getMenuItem(0).getId());
  }

  @Test void addProductToMenuItemZero()
  {
    addMenuItemOne();
    assertEquals(0,menuManager.getMenuItem(0).getProducts().size());
  }

  @Test void addProductToMenuItemOne()
  {
    addMenuItemOne();
    menuManager.addProductToMenuItem(0,modelManager.createProduct("Cheese"));
    assertEquals("Cheese",menuManager.getMenuItem(0).getProducts().getFirst().getName());
  }

  @Test void menuItemToStringOne()
  {
    addMenuItemOne();
    assertEquals("Id:1Name:1Allergies:1Price:1.0Products:{}",menuManager.menuItemToString(0));
  }
}