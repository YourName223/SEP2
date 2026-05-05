/*//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
  public static void main(String[] args)
  {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    System.out.printf("Hello and welcome!");

    for (int i = 1; i <= 5; i++)
    {
      //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
      // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
      System.out.println("i = " + i);
    }
  }
}

import Repository.MenuDAO;
import Repository.MenuDAOImpl;
import mediator.Server;
import model.Component;
import model.Ingredient;
import model.Model;
import model.ModelManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws SQLException
  {
    Model model = new ModelManager();

    Server server = new Server(model);
    Thread serverThread = new Thread(server);
    serverThread.setDaemon(false);
    serverThread.start();
    MenuDAO menuDAO = new MenuDAOImpl();

    ArrayList<String> names = menuDAO.getAllNames();

    MenuDAOImpl dao = MenuDAOImpl.getInstance();
    ArrayList<Component> products = dao.getAllProductsFromMenuItem("Pizza");
    for (Component c : products) {
      System.out.println(c.getName());
    }

    ArrayList<Ingredient> ingredients = dao.getAllIngredientsFromProduct("Bøf");
    System.out.println("=== Ingredienser i Bøf ===");
    for (Ingredient i : ingredients) {
      System.out.println("Navn: " + i.getName() + " | Enhed: " + i.getStock());
    }

    System.out.println("Names:"+names);
    System.out.println(menuDAO.readByName(names.getFirst()));

    System.out.println("Server kører på port 2910...");
  }
}*/

import Repository.MenuDAO;
import Repository.MenuDAOImpl;
import mediator.Server;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws SQLException
  {
    Model model = new ModelManager();

    Server server = new Server(model);
    Thread serverThread = new Thread(server);
    serverThread.setDaemon(false);
    serverThread.start();

    MenuDAOImpl dao = MenuDAOImpl.getInstance();

    // Test getAllNames
    ArrayList<String> names = dao.getAllNames();
    System.out.println("=== Menu navne ===");
    System.out.println(names);

    // Test getAllRecipesFromMenuItem
    System.out.println("\n=== Recipes i Burger menu ===");
    ArrayList<Recipe> recipes = dao.getAllRecipesFromMenuItem("Burger menu");
    for (Recipe c : recipes)
    {
      System.out.println(c.getName());
    }

    // Test getAllIngredientsFromRecipe
    System.out.println("\n=== Ingredienser i Burger ===");
    ArrayList<Ingredient> ingredients = dao.getAllIngredientsFromRecipe("Burger");
    for (Ingredient i : ingredients)
    {
      System.out.println("Navn: " + i.getName());
    }

    // Test getRecipeIdsFromMenuItem
    System.out.println("\n=== Recipe IDs i Pizza menu ===");
    List<String> recipeIds = dao.getRecipeIdsFromMenuItem("Pizza menu");
    System.out.println(recipeIds);

    // Test getRecipeWithIngredients med første recipe id
    if (!recipeIds.isEmpty())
    {
      System.out.println("\n=== Recipe med ingredienser (id: " + recipeIds.getFirst() + ") ===");
      Recipe recipe = dao.getRecipeWithIngredients(recipeIds.getFirst());
      System.out.println("Recipe: " + recipe.getName());
      for (Ingredient i : recipe.getIngredients())
      {
        System.out.println("  Ingrediens: " + i.getName());
      }
    }

    // Test readByName
    System.out.println("\n=== readByName: Burger menu ===");
    System.out.println(dao.readByName("Burger menu"));

    // Test createIngredient
    System.out.println("\n=== Opret ingrediens ===");
    Ingredient newIngredient = dao.createIngredient("Løg");
    System.out.println("Oprettet: " + newIngredient.getName() + " med id: " + newIngredient.getId());

    // Test createRecipe
    System.out.println("\n=== Opret recipe ===");
    ArrayList<Ingredient> recipeIngredients = new ArrayList<>();
    recipeIngredients.add(newIngredient);
    Recipe newRecipe = dao.createRecipe("Løgring", recipeIngredients);
    System.out.println("Oprettet: " + newRecipe.getName() + " med id: " + newRecipe.getId());

    // Test createMenu
    System.out.println("\n=== Opret menu ===");
    ArrayList<Recipe> menuRecipes = new ArrayList<>();
    menuRecipes.add(newRecipe);
    MenuItem newMenu = dao.createMenu("Løgring menu", "Gluten", 79, menuRecipes);
    System.out.println("Oprettet: " + newMenu.getName());

    System.out.println("\nServer kører på port 2910...");
  }
}