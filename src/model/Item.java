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

    public void aplicarEfecto(Heroe heroe) {

        if (tipoItem.equals("POCION_VIDA")) {

            int nuevaVida = heroe.getVidaActual() + valorEfecto;
            if (nuevaVida > heroe.getVidaMax()) {nuevaVida = heroe.getVidaMax();}

            heroe.setVidaActual(nuevaVida);}

        if (tipoItem.equals("ARMA_ATAQUE")) {
            heroe.setFuerzaAtaque(heroe.getFuerzaAtaque() + valorEfecto);}
    }

}