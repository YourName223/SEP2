package Repository;

import model.Menu;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
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
        return new Menu(generatedId, name, allergies, price);
      }
    }
    return null;
  }

  @Override public Menu readById(int id) throws SQLException
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

        return new Menu(id, name, allergies, price);
      }
      else
      {
        return null;
      }
    }
  }

  @Override public List<Menu> readByName(String searchString) throws SQLException
  {
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Menu WHERE name LIKE ?");
      statement.setString(1, "%" + searchString + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Menu> result = new ArrayList<>();
      while (resultSet.next())
      {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String allergies = resultSet.getString("allergies");
        double price = resultSet.getDouble("price");
        Menu menu = new Menu(id, name, allergies, price);
        result.add(menu);
      }
      return result;
    }
  }

  @Override public void update(Menu menu) throws SQLException
  {
    try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
    {
      PreparedStatement statement =
          connection.prepareStatement(
              "UPDATE menu SET name = ?, allergies = ?, price = ? WHERE id = ?");
      statement.setString(1, menu.getName());
      statement.setString(2, menu.getAllergies());
      statement.setDouble(3, menu.getPrice());
      statement.setInt(4, menu.getId());
      statement.executeUpdate();
    }
  }

  @Override public void delete(Menu menu) throws SQLException
  {
    try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
    {
      PreparedStatement statement =
          connection.prepareStatement("DELETE FROM menu WHERE id = ?");
      statement.setInt(1, menu.getId());
      statement.executeUpdate();
    }
  }
}
