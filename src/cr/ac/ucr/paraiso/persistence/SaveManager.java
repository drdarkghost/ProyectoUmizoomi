package cr.ac.ucr.paraiso.persistence;

import cr.ac.ucr.paraiso.model.Hero;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {

    public void saveGame(Hero hero) {

        List<String> data = new ArrayList<>();

        data.add(hero.getName());
        data.add(hero.getHeroClass());
        data.add(String.valueOf(hero.getCurrentHealth()));
        data.add(String.valueOf(hero.getAttackPower()));
        data.add(String.valueOf(hero.getGold()));
        data.add(String.valueOf(hero.hasKey()));
        data.add(String.valueOf(hero.getPosX()));
        data.add(String.valueOf(hero.getPosY()));

        try {

            Files.write(Path.of("partida.txt"), data);

        } catch (IOException e) {

            System.out.println("Error saving game.");

        }

    }

}