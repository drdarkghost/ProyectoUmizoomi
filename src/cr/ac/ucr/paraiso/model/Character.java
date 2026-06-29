package cr.ac.ucr.paraiso.model;

public class Character {

    private String name;
    private int maxHealth;
    private int currentHealth;
    private int attackPower;
    private int posX;
    private int posY;

    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Character(String name, int maxHealth, int attackPower,
                     int posX, int posY) {

        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attackPower = attackPower;
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void takeDamage(int amount) {

        currentHealth -= amount;

        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    public void attack(Character target) {
        target.takeDamage(attackPower);
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setName(String name) {this.name = name;}

    public void setMaxHealth(int maxHealth) {this.maxHealth = maxHealth;}

    public void setAttackPower(int attackPower) {this.attackPower = attackPower;}
}