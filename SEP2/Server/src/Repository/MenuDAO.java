package Repository;

import model.Component;
import model.Ingredient;
import model.MenuItem;
import model.Recipe;

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
  ArrayList<Component> getAllProductsFromMenuItem(String menuName);
  ArrayList<Ingredient> getAllIngredientsFromProduct(String productName);
  Recipe getRecipeWithIngredients(String recipeId);
  List<String> getRecipyIdsFromMenuItem(String name);
}