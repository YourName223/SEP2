package model;

public class TableManager
{
  public TableList tableList;

  public TableManager(TableList tableList)
  {
    this.tableList = tableList;
  }

  public void secureTable(String tableNr)
  {
    if(tableList.getTable(tableNr) == null)
    {
      tableList.addTable(tableNr);
    }
  }
}
