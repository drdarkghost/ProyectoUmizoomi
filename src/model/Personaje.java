package model;

public class Personaje {

    private String nombre;
    private int vidaMax;
    private int vidaActual;
    private int fuerzaAtaque;
    private int posX;
    private int posY;

    public Personaje(String nombre, int vidaMax, int fuerzaAtaque, int posX, int posY) {

        this.nombre = nombre;
        this.vidaMax = vidaMax;
        this.vidaActual = vidaMax;
        this.fuerzaAtaque = fuerzaAtaque;
        this.posX = posX;
        this.posY = posY;
    }

    public boolean estaVivo() {
        return vidaActual > 0;
    }

    public void recibirDano(int cantidad) {
        vidaActual -= cantidad;

        if (vidaActual < 0) {
            vidaActual = 0;
        }
    }

    public void atacar(Personaje objetivo) {
        objetivo.recibirDano(fuerzaAtaque);
    }

}