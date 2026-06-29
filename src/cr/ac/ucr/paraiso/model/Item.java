package cr.ac.ucr.paraiso.model;

public class Item {

    private String name;
    private String description;
    private String itemType;
    private int effectValue;

    public Item(String name,String description,String itemType,int effectValue) {

        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.effectValue = effectValue;
    }

    public void applyEffect(Hero hero) {

        if (itemType.equals("HEALTH_POTION")) {

            int newHealth = hero.getCurrentHealth() + effectValue;

            if (newHealth > hero.getMaxHealth()) {
                newHealth = hero.getMaxHealth();
            }

            hero.setCurrentHealth(newHealth);

        } else if (itemType.equals("ATTACK_WEAPON")) {

            hero.setAttackPower(hero.getAttackPower() + effectValue);
        }

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getItemType() {
        return itemType;
    }

    public int getEffectValue() {
        return effectValue;
    }

    public void setName(String name) {this.name = name;}

    public void setDescription(String description) {this.description = description;}

    public void setItemType(String itemType) {this.itemType = itemType;}

    public void setEffectValue(int effectValue) {this.effectValue = effectValue;}
}