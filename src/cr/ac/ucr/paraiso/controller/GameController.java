package cr.ac.ucr.paraiso.controller;

import cr.ac.ucr.paraiso.model.*;
import cr.ac.ucr.paraiso.persistence.SaveManager;
import cr.ac.ucr.paraiso.persistence.LoadManager;

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

    public Cell[][] getMap() {return gameMap.getMap();}

    public Hero getHero() {
        return hero;
    }

    private String fight(Enemy enemy) {

        while (hero.isAlive() && enemy.isAlive()) {

            hero.attack(enemy);

            if (enemy.isAlive()) {
                enemy.attack(hero);
            }

        }

        if (hero.isAlive()) {

            hero.setGold(
                    hero.getGold()
                            + enemy.getGoldReward());

            return "You defeated a "
                    + enemy.getMonsterType()
                    + "!";
        }

        return "Game Over";
    }

    private String checkEnemy(int row, int col) {

        Enemy enemy = gameMap.getEnemy(row, col);

        if (enemy == null) {
            return ".";
        }

        String result = fight(enemy);

        if (hero.isAlive()) {

            gameMap.removeEnemy(row, col);

            gameMap.setCell(row, col, 'H');

        }

        return result;

    }

    private String checkItem(int row, int col) {

        Item item = gameMap.getItem(row, col);

        if (item == null) {
            return ".";
        }

        if (hero.addItem(item)) {

            gameMap.removeItem(row, col);

            gameMap.setCell(row, col, 'H');

            return item.getName() + " added to inventory.";

        }

        return "Inventory Full.";

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

        if (hero.addItem(reward)) {

            gameMap.setCell(row, col, 'H');

            return reward.getName() + " added to inventory.";

        }

        return "Inventory Full.";

    }

    private String checkKey(int row, int col) {

        hero.setHasKey(true);

        gameMap.setCell(row, col, 'H');

        return "You found the key!";

    }

    private String checkDoor(int row, int col) {

        if (hero.hasKey()) {

            gameMap.setCell(hero.getPosX(), hero.getPosY(), '.');

            hero.setPosX(row);
            hero.setPosY(col);

            gameMap.setCell(row, col, 'H');

            return "Congratulations! You escaped the dungeon!";

        }

        return "The door is locked. Find the key first.";

    }

    public String moveHero(int newRow, int newCol) {

        if (newRow < 0 || newRow >= 9 || newCol < 0 || newCol >= 9) {
            return "You cannot leave the map.";
        }

        char destination = gameMap.getCell(newRow, newCol);

        if (destination == 'W') {
            return "You cannot move there.";
        }

        gameMap.moveHero(hero, newRow, newCol);

        if (destination == 'E') {

            return checkEnemy(newRow, newCol);

        }

        if (destination == 'I') {

            return checkItem(newRow, newCol);

        }

        if (destination == 'C') {

            return checkChest(newRow, newCol);

        }

        if (destination == 'K') {

            return checkKey(newRow, newCol);

        }

        if (destination == 'D') {

            return checkDoor(newRow, newCol);

        }

        return "Hero moved.";
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
}