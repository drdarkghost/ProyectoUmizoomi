package cr.ac.ucr.paraiso.model;

public class Map {

    private Cell[][] map;

    public Map() {

        map = new Cell[9][9];

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
                        30,
                        5,
                        2,
                        4,
                        15,
                        "Slime"
                )
        );

        map[4][8].setSymbol('E');
        map[4][8].setEnemy(
                new Enemy(
                        "Giga Slime",
                        70,
                        12,
                        4,
                        8,
                        35,
                        "Giga Slime"
                )
        );

        map[8][6].setSymbol('E');
        map[8][6].setEnemy(
                new Enemy(
                        "King Slime",
                        150,
                        20,
                        8,
                        6,
                        75,
                        "King Slime"
                )
        );

        map[8][8].setSymbol('C');

        map[7][2].setSymbol('K');

        map[0][3].setSymbol('I');
        map[0][3].setItem(

                new Item(
                        "Health Potion",
                        "Restores 25 HP",
                        "HEALTH_POTION",
                        25
                )
        );

        map[6][7].setSymbol('W');
        map[6][7].setWalkable(false);

        map[8][0].setSymbol('D');

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

        map[row][col].setEnemy(null);

        map[row][col].setSymbol('.');

    }

    public void removeItem(int row, int col) {

        map[row][col].setItem(null);

        map[row][col].setSymbol('.');

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
}