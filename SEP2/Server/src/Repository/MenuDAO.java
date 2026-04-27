package Repository;

import model.Menu;

import java.sql.SQLException;
import java.util.List;

public interface MenuDAO
{
  Menu create (int id,String name, String allergies, double price) throws SQLException;
  Menu readById (int id) throws SQLException;
  List<Menu> readByName (String searchString) throws SQLException;
  void update (Menu menu) throws SQLException;
  void delete (Menu menu) throws  SQLException;
}