  package Repository;

  import model.*;

  import java.sql.*;
  import java.util.ArrayList;
  import java.util.List;

  // try (Connection connection = DriverManager.getConnection("jdbc:postgresql://ep-mute-water-al8wg1w9-pooler.c-3.eu-central-1.aws.neon.tech/neondb", "neondb_owner", "npg_Jae8lwoZ5kdn"))

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
      try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
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

    @Override
    public ArrayList<Component> getAllRecipesFromMenuItem(String menuName)
    {
      ArrayList<Component> components = new ArrayList<>();
      try (Connection connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
      {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT r.id, r.name FROM recipe r " +
                "JOIN menu_recipe mr ON r.id = mr.recipe_id " +
                "WHERE mr.menu_name = ?");
        statement.setString(1, menuName);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
          String id   = resultSet.getString("id");
          String name = resultSet.getString("name");
          components.add(new Recipe(id, name));
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      return components;
    }

    @Override
    public ArrayList<Ingredient> getAllIngredientsFromRecipe(String recipeName)
    {
      ArrayList<Ingredient> ingredients = new ArrayList<>();
      try (Connection connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
      {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT i.id, i.name FROM ingredient i " +
                "JOIN recipe_ingredient ri ON i.id = ri.ingredient_id " +
                "JOIN recipe r ON r.id = ri.recipe_id " +
                "WHERE r.name = ?");
        statement.setString(1, recipeName);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
          Ingredient ingredient = new Ingredient();
          ingredient.setId(resultSet.getString("id"));
          ingredient.setName(resultSet.getString("name"));
          ingredients.add(ingredient);
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      return ingredients;
    }

    /*@Override public MenuItem readById(int id) throws SQLException
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

          return new MenuItem(name, allergies, price);
        }
        else
        {
          return null;
        }
      }
    }*/

    @Override public ArrayList<MenuItem> readByName(String searchString) throws SQLException
    {
      try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
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

    /*@Override public ArrayList<Component> getAllProductsFromMenuItem(String menuName)
    {
      ArrayList<Component> components = new ArrayList<>();
      try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
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
    }*/

    /*public ArrayList<Ingredient> getAllIngredientsFromProduct(String productName)
    {
      ArrayList<Ingredient> ingredients = new ArrayList<>();
      try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
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
    }*/

    @Override
    public Recipe getRecipeWithIngredients(String recipeId)
    {
      Recipe recipe = null;
      try (Connection connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
      {
        PreparedStatement recipeStatement = connection.prepareStatement(
            "SELECT id, name FROM recipe WHERE id = ?");
        recipeStatement.setInt(1, Integer.parseInt(recipeId));
        ResultSet recipeRs = recipeStatement.executeQuery();

        if (recipeRs.next())
        {
          String id   = recipeRs.getString("id");
          String name = recipeRs.getString("name");
          recipe = new Recipe(id, name);
        }

        if (recipe != null)
        {
          PreparedStatement ingStatement = connection.prepareStatement(
              "SELECT i.id, i.name FROM ingredient i " +
                  "JOIN recipe_ingredient ri ON i.id = ri.ingredient_id " +
                  "WHERE ri.recipe_id = ?");
          ingStatement.setInt(1, Integer.parseInt(recipeId));
          ResultSet ingRs = ingStatement.executeQuery();

          while (ingRs.next())
          {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(ingRs.getString("id"));
            ingredient.setName(ingRs.getString("name"));
            recipe.addIngredient(ingredient, 0);
          }
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      return recipe;
    }

    @Override
    public List<String> getRecipeIdsFromMenuItem(String menuName)
    {
      List<String> recipeIds = new ArrayList<>();
      try (Connection connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
      {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT recipe_id FROM menu_recipe WHERE menu_name = ?");
        statement.setString(1, menuName);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
          recipeIds.add(resultSet.getString("recipe_id"));
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      return recipeIds;
    }

    @Override
    public List<String> getRecipeNamesFromMenuItem(String menuName)
    {
      List<String> recipeIds = new ArrayList<>();
      try (Connection connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
      {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT recipe_id FROM menu_recipe WHERE menu_name = ?");
        statement.setString(1, menuName);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
          recipeIds.add(resultSet.getString("recipe_id"));
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      return recipeIds;
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

    public Ingredient createIngredient(String name) throws SQLException
    {
      try (Connection connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
      {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO ingredient(name) VALUES (?) RETURNING id");
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();

        if (rs.next())
        {
          Ingredient ingredient = new Ingredient();
          ingredient.setId(rs.getString("id"));
          ingredient.setName(name);
          return ingredient;
        }
      }
      return null;
    }

    public Recipe createRecipe(String name, ArrayList<Ingredient> ingredients) throws SQLException
    {
      try (Connection connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
      {
        // Insert recipe og få id tilbage
        PreparedStatement recipeStatement = connection.prepareStatement(
            "INSERT INTO recipe(name) VALUES (?) RETURNING id");
        recipeStatement.setString(1, name);
        ResultSet rs = recipeStatement.executeQuery();

        if (rs.next())
        {
          String id = rs.getString("id");
          Recipe recipe = new Recipe(id, name);

          // Kobl ingredienser til recipe
          PreparedStatement riStatement = connection.prepareStatement(
              "INSERT INTO recipe_ingredient(recipe_id, ingredient_id) VALUES (?, ?)");

          for (Ingredient ingredient : ingredients)
          {
            riStatement.setInt(1, Integer.parseInt(id));
            riStatement.setInt(2, Integer.parseInt(ingredient.getId()));
            riStatement.executeUpdate();
          }

          return recipe;
        }
      }
      return null;
    }

    public MenuItem createMenu(String name, String allergies, double price, ArrayList<Recipe> recipes) throws SQLException
    {
      try (Connection connection = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/postgres?currentSchema=jdbc", "postgres", "admin"))
      {
        // Insert menu
        PreparedStatement menuStatement = connection.prepareStatement(
            "INSERT INTO menu(name, allergies, price) VALUES (?, ?, ?)");
        menuStatement.setString(1, name);
        menuStatement.setString(2, allergies);
        menuStatement.setDouble(3, price);
        menuStatement.executeUpdate();

        MenuItem menuItem = new MenuItem(name, allergies, price);

        // Kobl recipes til menu
        PreparedStatement mrStatement = connection.prepareStatement(
            "INSERT INTO menu_recipe(menu_name, recipe_id) VALUES (?, ?)");

        for (Recipe recipe : recipes)
        {
          mrStatement.setString(1, name);
          mrStatement.setInt(2, Integer.parseInt(recipe.getId()));
          mrStatement.executeUpdate();
        }

        return menuItem;
      }
    }
  }
