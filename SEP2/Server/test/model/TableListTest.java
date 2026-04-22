package model;

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
    tableList = new TableList();
  }

  @Test void addTableZero()
  {
    tables.add(new Table("23"));
    assertEquals(new Table("23"),tables.getFirst());
  }

  @Test void getTableZero()
  {
    tableList.addTable("23");

    assertThrows(Exception.class, () -> tableList.getTable(null));
  }

  @Test void getTableOne()
  {
    tableList.addTable("25");
    assertEquals(new Table("23"),tables);
  }

  @Test void addOrderOne()
  {
    tableList.addTable("1");
    tableList.addOrder("1",new Order());
    assertEquals("",tableList.getTable("1").getOrders().getFirst().getContent());
  }
}