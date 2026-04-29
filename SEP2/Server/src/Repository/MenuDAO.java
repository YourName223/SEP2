package Repository;

import model.MenuItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MenuDAO
{
  /*MenuItem create (int id,String name, String allergies, double price) throws SQLException;*/
  MenuItem readById (int id) throws SQLException;
  List<MenuItem> readByName (String searchString) throws SQLException;
  /*void update (MenuItem menuItem) throws SQLException;
  void delete (MenuItem menuItem) throws  SQLException;*/
  ArrayList<String> getAllNames ();
}