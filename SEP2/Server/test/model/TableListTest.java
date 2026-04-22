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

  @Test void addTable()
  {
    tables.add(new Table("23"));
  }

  @Test void getTableZero1()
  {
    tableList.addTable("23");

    assertThrows(Exception.class, () -> tableList.getTable(null));
  }

  @Test void getTableZero2()
  {
    tableList.addTable("25");

    assertThrows(Exception.class, () -> tableList.getTable("23"));
  }

  @Test void getTableOne()
  {
    tableList.addTable("25");
    assertEquals("[25,[]]",tableList.getTable("25").toString());
  }

  @Test void addOrder()
  {
    tableList.getTable("1").assignOrder(new Order());
  }
}