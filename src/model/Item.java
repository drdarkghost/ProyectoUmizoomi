package model;

public class Item {

    private String nombre;
    private String descripcion;
    private String tipoItem;
    private int valorEfecto;

    public Item(String nombre, String descripcion, String tipoItem, int valorEfecto) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoItem = tipoItem;
        this.valorEfecto = valorEfecto;
    }

    public void aplicarEfecto(Hero hero) {

        if (tipoItem.equals("POCION_VIDA")) {

            int nuevaVida = hero.getVidaActual() + valorEfecto;
            if (nuevaVida > hero.getVidaMax()) {nuevaVida = hero.getVidaMax();}

            hero.setVidaActual(nuevaVida);}

        if (tipoItem.equals("ARMA_ATAQUE")) {
            hero.setFuerzaAtaque(hero.getFuerzaAtaque() + valorEfecto);}
    }

}