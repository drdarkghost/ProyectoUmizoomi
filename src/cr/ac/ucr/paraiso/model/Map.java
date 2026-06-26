package cr.ac.ucr.paraiso.model;

public class Map {

    private char[][] map;

    public Map() {
        map = new char[9][9];
        fillMap();
        placeElements();
    }

    private void fillMap() {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                map[row][col] = ' ';
            }
        }
    }

    private void placeElements() {
        map[0][0] = 'H';
        map[2][4] = 'E';
        map[0][3] = 'I';
        map[6][7] = 'W';
        map[8][8] = 'C';
    }

    public char[][] getMap() {
        return map;
    }

    public void setCell(int row, int col, char value) {
        map[row][col] = value;
    }

    public char getCell(int row, int col) {
        return map[row][col];
    }
}