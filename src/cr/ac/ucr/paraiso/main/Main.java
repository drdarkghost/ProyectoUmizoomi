package cr.ac.ucr.paraiso.main;

import cr.ac.ucr.paraiso.model.Hero;
import cr.ac.ucr.paraiso.model.Item;

public class Main {

    public static void main(String[] args) {

        Hero hero =
                new Hero("Gabriel", 100, 20, 0, 0, "Guerrero");

        Item pocion =
                new Item("Pocion", "Recupera vida", "POCION_VIDA", 25);

        hero.agregarItem(pocion);

        System.out.println("Heroe creado correctamente");
        System.out.println("Heroe: " + hero.getNombre());
        System.out.println("Clase: " + hero.getTipoClase());
    }
}