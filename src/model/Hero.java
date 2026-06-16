package model;

public class Hero extends Character {

    private String tipoClase;
    private int oroAcumulado;
    private boolean tieneLlave;
    private Item[] inventario;

    public Hero() {

        super();
        inventario = new Item[5];
        oroAcumulado = 0;
        tieneLlave = false;
    }

    public boolean agregarItem(Item item) {

        for (int i = 0; i < inventario.length; i++) {

            if (inventario[i] == null) {

                inventario[i] = item;
                return true;
            }
        }

        return false;
    }
}