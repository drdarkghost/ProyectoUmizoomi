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

    private Enemy enemyInCombat;

    private int enemyRow;
    private int enemyCol;

    private boolean combatActive;

    public GameController(Hero hero) {

        this.hero = hero;

        gameMap = new Map();

        nextChestPotion = true;

        saveManager = new SaveManager();
        loadManager = new LoadManager();

        enemyInCombat = null;

        enemyRow = -1;
        enemyCol = -1;

        combatActive = false;

    }

    public Cell[][] getMap() {

        return gameMap.getMap();

    }

    public Hero getHero() {

        return hero;

    }

    public boolean isCombatActive() {

        return combatActive;

    }

    private void startCombat(int row, int col) {

        enemyInCombat = gameMap.getEnemy(row, col);

        enemyRow = row;
        enemyCol = col;

        combatActive = true;

    }

    public String attackEnemy() {

        if (!combatActive) {

            return "There is no enemy nearby.";

        }

        String message = "";

        hero.attack(enemyInCombat);

        message += "You attacked " + enemyInCombat.getMonsterType() + ".\n";

        message += "Enemy HP: " + enemyInCombat.getCurrentHealth() + "/"
                + enemyInCombat.getMaxHealth() + "\n";

        if (!enemyInCombat.isAlive()) {

            hero.setGold(hero.getGold() + enemyInCombat.getGoldReward());

            gameMap.removeEnemy(enemyRow, enemyCol);

            gameMap.moveHero(hero, enemyRow, enemyCol);

            combatActive = false;

            enemyInCombat = null;

            message += "You defeated the enemy!\n";

            return message;

        }

        enemyInCombat.attack(hero);

        message += enemyInCombat.getMonsterType() + " attacked you.\n";

        message += "Hero HP: " + hero.getCurrentHealth() + "/" + hero.getMaxHealth();

        if (!hero.isAlive()) {

            combatActive = false;

            enemyInCombat = null;

            message += "\n====================================\n"
                    + "  GAME OVER\n\n"
                    + "Your hero has fallen.\n"
                    + "Gold collected: " + hero.getGold() + "\n"
                    + "Class: " + hero.getHeroClass() + "\n\n"
                    + "Better luck next time " + hero.getName() + "!" + "\n\n"
                    + "Press F9 to load a saved game."+ "\n"
                    + "====================================";

            return message;

        }

        return message;

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

        startCombat(row, col);

        Enemy enemy = gameMap.getEnemy(row, col);

        return "========== COMBAT ==========\n\n"
                + "Enemy: " + enemy.getMonsterType()
                + "\nHealth: " + enemy.getCurrentHealth()
                + "\nAttack: " + enemy.getAttackPower()
                + "\n\nPress SPACE to attack.";

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

        return "====================================\n"
                + "  VICTORY!\n\n"
                + "You escaped the dungeon!\n"
                + "Gold collected: " + hero.getGold() + "\n"
                + "Class: " + hero.getHeroClass() + "\n\n"
                + "Thanks for playing " + hero.getName() + "!" + "\n\n"
                + "Press F9 to load a saved game."+ "\n"
                + "====================================";

    }

    public String moveHero(int newRow, int newCol) {

        if (!hero.isAlive()) {

            return "Game Over.";

        }

        if (combatActive) {

            return "You are in combat! Press SPACE to attack.";

        }

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