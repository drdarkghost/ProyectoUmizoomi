package cr.ac.ucr.paraiso.model;

public class Cell {

    private char symbol;
    private boolean walkable;
    private Item item;
    private Enemy enemy;

    public Cell(char symbol, boolean walkable) {

        this.symbol = symbol;
        this.walkable = walkable;
        this.item = null;
        this.enemy = null;

    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

}