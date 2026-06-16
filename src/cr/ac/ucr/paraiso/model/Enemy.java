package cr.ac.ucr.paraiso.model;

public class Enemy extends Character {

    private int goldReward;
    private String monsterType;

    public Enemy(String name,int maxHealth,int attackPower,
                 int posX, int posY, int goldReward, String monsterType) {

        super(name, maxHealth, attackPower, posX, posY);

        this.goldReward = goldReward;
        this.monsterType = monsterType;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public String getMonsterType() {
        return monsterType;
    }

}