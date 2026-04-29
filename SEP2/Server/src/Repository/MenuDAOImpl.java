package Repository;

import model.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO
{
  private static MenuDAOImpl instance;

  public MenuDAOImpl() throws SQLException
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

  /*@Override
  public MenuItem create (int id, String name, String allergies, double price) throws SQLException
  {
    try (Connection connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc",
        "postgres", "admin"))
    {
      PreparedStatement statement = connection.prepareStatement(
          "INSERT INTO menu(name, allergies, price) VALUES (?,?,?) RETURNING id");

      statement.setString(1, name);
      statement.setString(2, allergies);
      statement.setDouble(3, price);

      ResultSet rs = statement.executeQuery();

      if (rs.next())
      {
        int generatedId = rs.getInt("id");
        return new MenuItem(generatedId, name, allergies, price);
      }
    }
    return null;
  }*/

  @Override public ArrayList<String> getAllNames()
  {
    ArrayList<String> names = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT name FROM Menu");
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        names.add(resultSet.getString("name"));
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return names;
  }

  @Override public MenuItem readById(int id) throws SQLException
  {
    try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Menu WHERE id = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next())
      {
        String name = resultSet.getString("name");
        String allergies = resultSet.getString("allergies");
        double price = resultSet.getDouble("price");

        return new MenuItem(id, name, allergies, price);
      }
      else
      {
        return null;
      }
    }
  }

  @Override public List<MenuItem> readByName(String searchString) throws SQLException
  {
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Menu WHERE name LIKE ?");
      statement.setString(1, "%" + searchString + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<MenuItem> result = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String allergies = resultSet.getString("allergies");
        double price = resultSet.getDouble("price");
        MenuItem menuItem = new MenuItem(id, name, allergies, price);
        result.add(menuItem);
      }
      return result;
    }
  }

  /*@Override public void update(MenuItem menuItem) throws SQLException
  {
    try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
    {
      PreparedStatement statement =
          connection.prepareStatement(
              "UPDATE menu SET name = ?, allergies = ?, price = ? WHERE id = ?");
      statement.setString(1, menuItem.getName());
      statement.setString(2, menuItem.getAllergies());
      statement.setDouble(3, menuItem.getPrice());
      statement.setInt(4, menuItem.getId());
      statement.executeUpdate();
    }
  }

  @Override public void delete(MenuItem menuItem) throws SQLException
  {
    try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
    {
      PreparedStatement statement =
          connection.prepareStatement("DELETE FROM menu WHERE id = ?");
      statement.setInt(1, menuItem.getId());
      statement.executeUpdate();
    }
  }*/
}
