package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableManagerTest
{
  TableManager tableManager;
  TableList tableList;


  @BeforeEach void setUp()
  {
    tableList = new TableList();
    tableManager = new TableManager(tableList);
  }

  @Test void secureTableZero()
  {
    assertEquals("[,[]]",tableList.toString());
    tableManager.secureTable("23");
    assertEquals("[23,[]]",tableList.toString());
  }
  @Test void secureTableOne()
  {
    tableManager.secureTable("23");
    assertEquals("[23,[]]",tableList.toString());
    tableManager.secureTable("23");
    assertEquals("[23,[]]",tableList.toString());
  }
}