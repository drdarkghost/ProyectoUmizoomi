package cr.ac.ucr.paraiso.main;

import cr.ac.ucr.paraiso.controller.GameController;
import cr.ac.ucr.paraiso.model.Hero;
import cr.ac.ucr.paraiso.view.Interfaz;

import java.util.Scanner;

public class Main {public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.println("===== ROGUE CONSOLE =====");
    System.out.print("Enter your hero's name: ");
    String heroName = scanner.nextLine();

    System.out.println("\nChoose your class:");
    System.out.println("1. Warrior");
    System.out.println("2. Tank");
    System.out.println("3. Assassin");

    System.out.print("Option: ");
    int option = scanner.nextInt();

    String heroClass;

    switch (option) {

        case 2:
            heroClass = "Tank";
            break;

        case 3:
            heroClass = "Assassin";
            break;

        default:
            heroClass = "Warrior";
            break;
    }

    Hero hero = new Hero(heroName, heroClass);

    GameController controller = new GameController(hero);

    new Interfaz(controller);
}
}