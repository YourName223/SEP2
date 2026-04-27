package model;

public class TableManager
{
  public TableList tableList;

  public TableManager()
  {
    this.tableList = new TableList();
  }

  public Table secureTable(String tableNr)
  {
    if(tableList.getTable(tableNr) == null)
    {
      tableList.addTable(tableNr);
    }
    return tableList.getTable(tableNr);
  }
}
