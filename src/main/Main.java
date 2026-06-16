package main;

import model.Heroe;
import model.Item;

public class Main {

    public static void main(String[] args) {

        Heroe heroe =
                new Heroe("Gabriel", 100, 20, 0, 0, "Guerrero");

        Item pocion =
                new Item("Pocion", "Recupera vida", "POCION_VIDA", 25);

        heroe.agregarItem(pocion);

        System.out.println("Heroe creado correctamente");
        System.out.println("Heroe: " + heroe.getNombre());
        System.out.println("Clase: " + heroe.getTipoClase());
    }
}