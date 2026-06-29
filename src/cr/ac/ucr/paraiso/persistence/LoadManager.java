package cr.ac.ucr.paraiso.persistence;

import cr.ac.ucr.paraiso.model.Hero;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LoadManager {

    public Hero loadGame() {

        try {

            List<String> data =
                    Files.readAllLines(Path.of("partida.txt"));

            Hero hero = new Hero(

                    data.get(0),
                    data.get(1)

            );

            hero.setCurrentHealth(

                    Integer.parseInt(data.get(2))

            );

            hero.setAttackPower(

                    Integer.parseInt(data.get(3))

            );

            hero.setGold(

                    Integer.parseInt(data.get(4))

            );

            hero.setHasKey(

                    Boolean.parseBoolean(data.get(5))

            );

            hero.setPosX(

                    Integer.parseInt(data.get(6))

            );

            hero.setPosY(

                    Integer.parseInt(data.get(7))

            );

            return hero;

        } catch (IOException e) {

            return null;

        }

    }

}