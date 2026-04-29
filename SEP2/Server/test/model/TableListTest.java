import model.Order;
import model.Table;
import model.TableList;
import model.TableOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TableListTest
{
  ArrayList<Table> tables;
  TableList tableList;

  @BeforeEach void setUp()
  {
    tables = new ArrayList<>();
    tableList = new TableList();
  }

  @Test void addTableZero()
  {
    Table table = new Table("23");

    tables.add(table);
    assertEquals(table,tables.getFirst());
  }

  @Test void getTableOne()
  {
    Table table = new Table("23");

    tables.add(table);
    tableList.addTable("23");

    assertEquals(table.getTableNr(), tableList.getTable("23").getTableNr());
  }

  @Test void addOrderOne()
  {
    tableList.addTable("1");
    //tableList.addOrder("1",new TableOrder("An order"));
    assertNotEquals(null,tableList.getTable("1").getOrders().getFirst());
  }
}