package Repository;

import model.Menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MenuDAOImpl implements MenuDAO
{
  private static MenuDAOImpl instance;

  private MenuDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized MenuDAOImpl getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new MenuDAOImpl();
    }
    return instance;
  }

  @Override
  public Menu create (int id, String name, String allergies, double price) throws SQLException
  {
    try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
    {
      PreparedStatement statement =
          connection.prepareStatement("INSERT INTO Menu(id,name, allergies, price) VALUES (?,?,?,?);");
      statement.setInt(1, id);
      statement.setString(2, name);
      statement.setString(3, allergies);
      statement.setDouble(4, price);

      statement.executeUpdate();
      return new Menu(id, name, allergies, price);
    }
  }

  @Override public Menu readById(int id) throws SQLException
  {
    return null;
  }

  @Override public List<Menu> readByName(String searchString)
      throws SQLException
  {
    return List.of();
  }

  @Override public void update(Menu menu) throws SQLException
  {

  }

  @Override public void delete(Menu menu) throws SQLException
  {

  }
}
