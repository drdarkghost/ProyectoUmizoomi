package cr.ac.ucr.paraiso.persistence;

import cr.ac.ucr.paraiso.model.Hero;
import cr.ac.ucr.paraiso.model.Item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {

    public void saveGame(Hero hero) {

        List<String> data = new ArrayList<>();

        data.add("=== HERO ===");
        data.add(hero.getName());
        data.add(hero.getHeroClass());
        data.add(String.valueOf(hero.getCurrentHealth()));
        data.add(String.valueOf(hero.getAttackPower()));
        data.add(String.valueOf(hero.getGold()));
        data.add(String.valueOf(hero.hasKey()));
        data.add(String.valueOf(hero.getPosX()));
        data.add(String.valueOf(hero.getPosY()));

        data.add("");

        data.add("=== INVENTORY ===");

        for (int i = 0; i < hero.getInventory().length; i++) {

            Item item = hero.getItem(i);

            if (item == null) {

                data.add("EMPTY");

            } else {

                data.add(item.getName());

            }

        }

        try {

            Files.write(Path.of("partida.txt"), data);

        } catch (IOException e) {

            System.out.println("Error saving game.");

        }

    }

}