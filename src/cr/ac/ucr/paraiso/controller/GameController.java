package cr.ac.ucr.paraiso.controller;

import cr.ac.ucr.paraiso.model.*;
import cr.ac.ucr.paraiso.persistence.LoadManager;
import cr.ac.ucr.paraiso.persistence.SaveManager;

public class GameController {

    private Map gameMap;
    private Hero hero;

    private boolean nextChestPotion;

    private SaveManager saveManager;
    private LoadManager loadManager;

    public GameController(Hero hero) {

        this.hero = hero;

        gameMap = new Map();

        nextChestPotion = true;

        saveManager = new SaveManager();
        loadManager = new LoadManager();

    }

    public Cell[][] getMap() {

        return gameMap.getMap();

    }

    public Hero getHero() {

        return hero;

    }

    public void saveGame() {

        saveManager.saveGame(hero);

    }

    public void loadGame() {

        Hero loadedHero = loadManager.loadGame();

        if (loadedHero != null) {

            hero = loadedHero;

            gameMap.placeHero(hero);

        }

    }

    private String fight(Enemy enemy) {

        while (hero.isAlive() && enemy.isAlive()) {

            hero.attack(enemy);

            if (enemy.isAlive()) {

                enemy.attack(hero);

            }

        }

        if (!hero.isAlive()) {

            return "Game Over.";

        }

        hero.setGold(
                hero.getGold()
                        + enemy.getGoldReward());

        return "You defeated "
                + enemy.getMonsterType()
                + ".";

    }

    private String checkEnemy(int row, int col) {

        Enemy enemy = gameMap.getEnemy(row, col);

        if (enemy == null) {

            return "There is no enemy.";

        }

        String result = fight(enemy);

        if (hero.isAlive()) {

            gameMap.removeEnemy(row, col);

            gameMap.moveHero(hero, row, col);

        }

        return result;

    }

    private String checkItem(int row, int col) {

        Item item = gameMap.getItem(row, col);

        if (item == null) {

            return "There is no item.";

        }

        if (!hero.addItem(item)) {

            return "Inventory Full.";

        }

        gameMap.removeItem(row, col);

        gameMap.moveHero(hero, row, col);

        return item.getName() + " added to inventory.";

    }

    private String checkChest(int row, int col) {

        Item reward;

        if (nextChestPotion) {

            reward = new Item(
                    "Health Potion",
                    "Restores 25 HP",
                    "HEALTH_POTION",
                    25
            );

        } else {

            reward = new Item(
                    "Attack Weapon",
                    "Increase attack by 10",
                    "ATTACK_WEAPON",
                    10
            );

        }

        nextChestPotion = !nextChestPotion;

        if (!hero.addItem(reward)) {

            return "Inventory Full.";

        }

        gameMap.clearCell(row, col);

        gameMap.moveHero(hero, row, col);

        return reward.getName() + " added to inventory.";

    }

    private String checkKey(int row, int col) {

        hero.setHasKey(true);

        gameMap.clearCell(row, col);

        gameMap.moveHero(hero, row, col);

        return "You found the key!";

    }

    private String checkDoor(int row, int col) {

        if (!hero.hasKey()) {

            return "The door is locked. Find the key first.";

        }

        gameMap.moveHero(hero, row, col);

        return "Congratulations! You escaped the dungeon!";

    }

    public String moveHero(int newRow, int newCol) {

        if (newRow < 0 || newRow >= 12 || newCol < 0 || newCol >= 12) {

            return "You cannot leave the map.";

        }

        if (!gameMap.isWalkable(newRow, newCol)) {

            return "You cannot move there.";

        }

        char destination = gameMap.getCell(newRow, newCol);

        switch (destination) {

            case 'E':
                return checkEnemy(newRow, newCol);

            case 'I':
                return checkItem(newRow, newCol);

            case 'C':
                return checkChest(newRow, newCol);

            case 'K':
                return checkKey(newRow, newCol);

            case 'D':
                return checkDoor(newRow, newCol);

            default:

                gameMap.moveHero(hero, newRow, newCol);

                return "Hero moved.";

        }

    }

}