package cr.ac.ucr.paraiso.controller;

import cr.ac.ucr.paraiso.model.Hero;
import cr.ac.ucr.paraiso.model.Map;

public class GameController {

    private Map gameMap;
    private Hero hero;

    public GameController() {
        gameMap = new Map();
        hero = new Hero("Lechuguin", 100, 20, 0, 0, "caballero");
    }

    public char[][] getMap() {
        return gameMap.getMap();
    }

    public Hero getHero() {
        return hero;
    }

    public String moveHero(int newRow, int newCol) {

        if (newRow < 0 || newRow >= 9 || newCol < 0 || newCol >= 9) {
            return "No puedes salir de los limites del mapa";
        }

        char destination = gameMap.getCell(newRow, newCol);

        if (destination == 'W') {
            return "No puedes avanzar aqui, hay una pared";
        }

        gameMap.setCell(hero.getPosX(), hero.getPosY(), ' ');

        hero.setPosX(newRow);
        hero.setPosY(newCol);

        gameMap.setCell(newRow, newCol, 'H');

        if (destination == 'E') {
            return "Encontraste un enemigo!!";
        }

        if (destination == 'I') {
            return "Encontraste un Item!!";
        }

        if (destination == 'C') {
            return "Encontraste un cofre!!";
        }

        return "El heroe se ha movido";
    }
}