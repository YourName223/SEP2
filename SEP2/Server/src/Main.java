import javafx.application.Application;
import mediator.Server;
import model.*;

import java.sql.SQLException;

public class Main {
  public static Model model;
  public static void main(String[] args) throws SQLException
  {
    model = new ModelManager();

    Server server = new Server(model);
    Thread serverThread = new Thread(server);
    serverThread.setDaemon(false);
    serverThread.start();

    /*
    MenuDao dao = new MenuDao();
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
*/


    System.out.println("\nServer kører på port 2910...");
    Application.launch(MyApplication.class);
  }
}