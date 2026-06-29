package cr.ac.ucr.paraiso.model;

public class Enemy extends Character {

    private int goldReward;
    private String monsterType;

    public Enemy(String name, int maxHealth, int attackPower,
                 int posX, int posY,
                 int goldReward,
                 String monsterType) {

        super(name, maxHealth, attackPower, posX, posY);

        this.goldReward = goldReward;
        this.monsterType = monsterType;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public void setGoldReward(int goldReward) {
        this.goldReward = goldReward;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

}