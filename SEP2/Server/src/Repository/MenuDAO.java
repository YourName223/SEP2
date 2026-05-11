package Repository;

import model.Ingredient;
import model.MenuItem;
import model.Recipe;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MenuDAO
{
  ArrayList<MenuItem> readByName(String searchString) throws SQLException; // eller behold List<MenuItem>
  ArrayList<String> getAllMenuNames();
  ArrayList<Recipe> getAllRecipesFromMenuItem(String menuName);
  ArrayList<Ingredient> getAllIngredientsFromRecipe(String recipeName);
  ArrayList<String> getRecipeNamesFromMenuItem(String menuName);
  Recipe getRecipeWithIngredients(String recipeId);
  ArrayList<String> getRecipeIdsFromMenuItemName(String name);
  Ingredient createIngredient(String name) throws SQLException;
  Recipe createRecipe(String name, ArrayList<Ingredient> ingredients) throws SQLException;
  MenuItem createMenu(String name, String allergies, double price, ArrayList<Recipe> recipes) throws SQLException;
  List<Ingredient> getStock();
  void setAmountOnIngredient(String id, double amountToRemove);
}





/*public interface MenuDAO
{
  MenuItem create (int id,String name, String allergies, double price) throws SQLException;
  MenuItem readById (int id) throws SQLException;
  List<MenuItem> readByName (String searchString) throws SQLException;
  /*void update (MenuItem menuItem) throws SQLException;
  void delete (MenuItem menuItem) throws  SQLException;
  ArrayList<String> getAllNames ();
  ArrayList<Component> getAllProductsFromMenuItem(String menuName);
  ArrayList<Ingredient> getAllIngredientsFromProduct(String productName);
  Recipe getRecipeWithIngredients(String recipeId);
  List<String> getRecipyIdsFromMenuItem(String name);
}*/