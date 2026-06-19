package cr.ac.ucr.paraiso.model;

public class Map {
    public static void main(String[] args){
        int [][] map = new int[5][5]; //
        map[0][0] = 'H';
        map[2][4] = 'E';
        map[0][3] = 'I';

        for(int fila = 0; fila < map.length; fila++){
            for(int col = 0; col < map[fila].length; col++){

            }
        } //Repetir para completar el mapa
            for(int count = 0; count < map.length; count++) {
                System.out.print(" _");
            }
            System.out.println();

            for(int count = 0; count < map.length; count++) {
                System.out.print(" |");
            }
            System.out.println();

        // Imprimir matriz del mapa


    }
}
