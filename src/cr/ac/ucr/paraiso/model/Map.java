package cr.ac.ucr.paraiso.model;

public class Map {
    public static void main(String[] args){
        char [][] map = new char[9][9];//
        for(int fila = 0; fila < map.length; fila++){

            for(int col = 0; col < map[fila].length; col++){
                map[fila][col] = ' ';

            }
        }

        map[0][0] = 'H'; //Objetos dentro del mapa, random
        map[2][4] = 'E';
        map[0][3] = 'I';
        map[6][7] = 'W';
        map[8][8] = 'C';


        System.out.println(" Mapa de Juego ");
        for (int fila = 0; fila < map.length; fila++) {

            // línea de arriba
            for(int col = 0; col < map[fila].length; col++){
                System.out.print("+---");
            }
            System.out.println("+");
            //Contenido de los cuadritos
            for (int col = 0; col < map[fila].length; col++) {
                System.out.print("| " + map[fila][col] + " ");

            }
            System.out.println("|");

        }
    }
}
