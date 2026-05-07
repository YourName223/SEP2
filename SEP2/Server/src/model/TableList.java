package model;

import java.util.ArrayList;

public class TableList
{
  private ArrayList<Table> tables;

  public TableList()
  {
    tables = new ArrayList<>();
  }

  public void addTable(String tableNr)
  {
    tables.add(new Table(tableNr));
  }

  public void addOrder(String tableNr, Order order)
  {
    getTable(tableNr).assignOrder(order);
  }

  public void removeOrder(Order order, String tableNr)
  {
    getTable(tableNr).removeOrder(order);
  }

  public Table getTable(String tableNr)
  {
    for (Table table : tables)
    {
      if (table.getTableNr().equals(tableNr))
      {
        return table.copy();
      }
    }
    return null;
  }
}
