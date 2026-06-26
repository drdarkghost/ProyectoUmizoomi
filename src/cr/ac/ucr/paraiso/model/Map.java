package cr.ac.ucr.paraiso.model;

public class Map {
    private char[][] map;

        public Map() {
            map = new char[9][9];
            initializeMap();
            placeElements();
        }

        private void initializeMap() {
            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map[row].length; col++) {
                    map[row][col] = ' ';
                }
            }
        }

        private void placeElements() {
            map[0][0] = 'H'; // Hero
            map[2][4] = 'E'; // Enemy
            map[0][3] = 'I'; // Item
            map[6][7] = 'W'; // Wall
            map[8][8] = 'C'; // Chest
        }

        public char[][] getMap() {
            return map;
        }
    }