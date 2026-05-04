  package Repository;

  import model.*;

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
      try (Connection connection = DriverManager.getConnection("jdbc:postgresql://ep-mute-water-al8wg1w9-pooler.c-3.eu-central-1.aws.neon.tech/neondb", "neondb_owner", "npg_Jae8lwoZ5kdn"))
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
      try(Connection connection = DriverManager.getConnection("jdbc:postgresql://ep-mute-water-al8wg1w9-pooler.c-3.eu-central-1.aws.neon.tech/neondb", "neondb_owner", "npg_Jae8lwoZ5kdn"))
      {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Menu WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
        {
          String name = resultSet.getString("name");
          String allergies = resultSet.getString("allergies");
          double price = resultSet.getDouble("price");

          return new MenuItem(name, allergies, price);
        }
        else
        {
          return null;
        }
      }
    }

    @Override public List<MenuItem> readByName(String searchString) throws SQLException
    {
      try (Connection connection = DriverManager.getConnection("jdbc:postgresql://ep-mute-water-al8wg1w9-pooler.c-3.eu-central-1.aws.neon.tech/neondb", "neondb_owner", "npg_Jae8lwoZ5kdn"))
      {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Menu WHERE name LIKE ?");
        statement.setString(1, "%" + searchString + "%");
        ResultSet resultSet = statement.executeQuery();
        ArrayList<MenuItem> result = new ArrayList<>();
        while (resultSet.next())
        {

          String name = resultSet.getString("name");
          String allergies = resultSet.getString("allergies");
          double price = resultSet.getDouble("price");
          MenuItem menuItem = new MenuItem(name, allergies, price);
          result.add(menuItem);
        }
        return result;
      }
    }

    @Override public ArrayList<Component> getAllProductsFromMenuItem(String menuName)
    {
      ArrayList<Component> components = new ArrayList<>();
      try (Connection connection = DriverManager.getConnection("jdbc:postgresql://ep-mute-water-al8wg1w9-pooler.c-3.eu-central-1.aws.neon.tech/neondb", "neondb_owner", "npg_Jae8lwoZ5kdn"))
      {
        PreparedStatement statement = connection.prepareStatement("SELECT p.name FROM product p JOIN menu_product mp ON p.name = mp.product_name WHERE mp.menu_name = ?");
        statement.setString(1, menuName);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
          String name = resultSet.getString("name");
          components.add(new Product(name));
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      return components;
    }

    public ArrayList<Ingredient> getAllIngredientsFromProduct(String productName)
    {
      ArrayList<Ingredient> ingredients = new ArrayList<>();
      try (Connection connection = DriverManager.getConnection(
          "jdbc:postgresql://ep-mute-water-al8wg1w9-pooler.c-3.eu-central-1.aws.neon.tech/neondb",
          "neondb_owner", "npg_Jae8lwoZ5kdn"))
      {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT i.name, i.unit FROM ingredient i " +
                "JOIN product_ingredient pi ON i.name = pi.ingredient_name " +
                "WHERE pi.product_name = ?");
        statement.setString(1, productName);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
          String name = resultSet.getString("name");
          String unit = resultSet.getString("unit");
          ingredients.add(new Ingredient());
          //ingredients.add(new Ingredient(name, unit));
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      return ingredients;
    }

    @Override public Recipe getRecipeWithIngredients(String recipeId)
    {
      return new Recipe("2","4");
    }

    public List<String> getRecipyIdsFromMenuItem(String name)
    {
      List<String> string = new ArrayList<>();
      string.add("2");
      return string;
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
