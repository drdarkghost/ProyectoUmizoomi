package cr.ac.ucr.paraiso.model;

public class Map {

    private Cell[][] map;

    public Map() {

        map = new Cell[12][12];

        initializeMap();

    }

    private void initializeMap() {

        for (int row = 0; row < map.length; row++) {

            for (int col = 0; col < map[row].length; col++) {

                map[row][col] = new Cell('.', true);

            }

        }

        map[0][0].setSymbol('H');

        map[2][4].setSymbol('E');
        map[2][4].setEnemy(
                new Enemy(
                        "Slime",
                        40,
                        8,
                        2,
                        4,
                        15,
                        "Slime"
                )
        );

        map[6][5].setSymbol('E');
        map[6][5].setEnemy(
                new Enemy(
                        "Slime",
                        40,
                        8,
                        6,
                        5,
                        15,
                        "Slime"
                )
        );

        map[9][2].setSymbol('E');
        map[9][2].setEnemy(
                new Enemy(
                        "Slime",
                        40,
                        8,
                        9,
                        2,
                        15,
                        "Slime"
                )
        );

        map[3][9].setSymbol('E');
        map[3][9].setEnemy(
                new Enemy(
                        "Slime",
                        40,
                        8,
                        3,
                        9,
                        15,
                        "Slime"
                )
        );

        map[4][8].setSymbol('E');
        map[4][8].setEnemy(
                new Enemy(
                        "Giga Slime",
                        80,
                        15,
                        4,
                        1,
                        40,
                        "Giga Slime"
                )
        );

        map[8][6].setSymbol('E');
        map[8][6].setEnemy(
                new Enemy(
                        "Giga Slime",
                        80,
                        15,
                        8,
                        6,
                        40,
                        "Giga Slime"
                )
        );

        map[8][6].setSymbol('E');
        map[8][6].setEnemy(
                new Enemy(
                        "King Slime",
                        180,
                        22,
                        10,
                        8,
                        100,
                        "King Slime"
                )
        );

        map[8][8].setSymbol('C');
        map[1][7].setSymbol('C');
        map[10][3].setSymbol('C');

        map[8][2].setSymbol('K');

        map[4][5].setSymbol('I');
        map[4][5].setItem(

                new Item(
                        "Attack Weapon",
                        "Increase attack by 10",
                        "ATTACK_WEAPON",
                        10
                )

        );

        map[0][3].setSymbol('I');
        map[0][3].setItem(

                new Item(
                        "Health Potion",
                        "Restores 25 HP",
                        "HEALTH_POTION",
                        25
                )
        );

        map[5][5].setSymbol('I');
        map[5][5].setItem(

                new Item(
                        "Attack Weapon",
                        "Increase attack by 10",
                        "ATTACK_WEAPON",
                        10
                )
        );

        map[6][7].setSymbol('W');
        map[6][7].setWalkable(false);

        map[3][3].setSymbol('W');
        map[3][3].setWalkable(false);

        map[5][2].setSymbol('W');
        map[5][2].setWalkable(false);

        map[1][3].setSymbol('W');
        map[1][3].setWalkable(false);

        map[4][8].setSymbol('W');
        map[4][8].setWalkable(false);

        map[6][3].setSymbol('W');
        map[6][3].setWalkable(false);

        map[9][4].setSymbol('W');
        map[9][4].setWalkable(false);

        map[11][0].setSymbol('D');

    }

    public void clearCell(int row, int col) {

        map[row][col].setEnemy(null);
        map[row][col].setItem(null);
        map[row][col].setSymbol('.');

    }

    public boolean isEmpty(int row, int col) {

        return map[row][col].getSymbol() == '.';

    }

    public void placeHero(Hero hero) {

        for (int row = 0; row < map.length; row++) {

            for (int col = 0; col < map[row].length; col++) {

                if (map[row][col].getSymbol() == 'H') {

                    map[row][col].setSymbol('.');

                }

            }

        }

        map[hero.getPosX()][hero.getPosY()].setSymbol('H');

    }

    public boolean hasSymbol(int row, int col, char symbol) {

        return map[row][col].getSymbol() == symbol;

    }

    public void moveHero(Hero hero, int newRow, int newCol) {

        map[hero.getPosX()][hero.getPosY()].setSymbol('.');

        hero.setPosX(newRow);
        hero.setPosY(newCol);

        map[newRow][newCol].setSymbol('H');

    }

    public Cell[][] getMap() {
        return map;
    }

    public char getCell(int row, int col) {

        return map[row][col].getSymbol();

    }

    public void setCell(int row, int col, char symbol) {

        map[row][col].setSymbol(symbol);

    }

    public boolean isWalkable(int row, int col) {

        return map[row][col].isWalkable();

    }

    public Enemy getEnemy(int row, int col) {

        return map[row][col].getEnemy();

    }

    public Item getItem(int row, int col) {

        return map[row][col].getItem();

    }

    public void removeEnemy(int row, int col) {

        clearCell(row, col);

    }

    public void removeItem(int row, int col) {

        clearCell(row, col);

    }

}