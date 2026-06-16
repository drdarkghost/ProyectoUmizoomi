package cr.ac.ucr.paraiso.main;

import cr.ac.ucr.paraiso.model.Hero;
import cr.ac.ucr.paraiso.model.Item;

public class Main {

    public static void main(String[] args) {

        Hero hero =
                new Hero("Gabriel", 100, 20, 0, 0, "Warrior");

        Item potion =
                new Item("Health Potion", "Restores health", "HEALTH_POTION", 25);

        hero.addItem(potion);

        System.out.println("Hero created successfully");
    }
}