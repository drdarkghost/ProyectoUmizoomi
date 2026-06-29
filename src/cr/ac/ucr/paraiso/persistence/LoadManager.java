package cr.ac.ucr.paraiso.persistence;

import cr.ac.ucr.paraiso.model.Hero;
import cr.ac.ucr.paraiso.model.Item;

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

                    data.get(1),
                    data.get(2)

            );

            hero.setCurrentHealth(

                    Integer.parseInt(data.get(3))

            );

            hero.setAttackPower(

                    Integer.parseInt(data.get(4))

            );

            hero.setGold(

                    Integer.parseInt(data.get(5))

            );

            hero.setHasKey(

                    Boolean.parseBoolean(data.get(6))

            );

            hero.setPosX(

                    Integer.parseInt(data.get(7))

            );

            hero.setPosY(

                    Integer.parseInt(data.get(8))

            );

            for (int i = 0; i < 5; i++) {

                String itemName = data.get(11 + i);

                if (!itemName.equals("EMPTY")) {

                    if (itemName.equals("Health Potion")) {

                        hero.setItem(
                                i,
                                new Item(
                                        "Health Potion",
                                        "Restores 25 HP",
                                        "HEALTH_POTION",
                                        25
                                )
                        );

                    }

                    else if (itemName.equals("Attack Weapon")) {

                        hero.setItem(
                                i,
                                new Item(
                                        "Attack Weapon",
                                        "Increase attack by 10",
                                        "ATTACK_WEAPON",
                                        10
                                )
                        );

                    }

                }

            }

            return hero;

        } catch (IOException e) {

            return null;

        }

    }

}