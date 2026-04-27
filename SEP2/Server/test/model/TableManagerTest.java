import model.TableList;
import model.TableManager;
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
    tableManager = new TableManager();
  }

  @Test void secureTableOne()
  {
    tableManager.secureTable("23");
    assertNotEquals(null,tableList.toString());
  }
}