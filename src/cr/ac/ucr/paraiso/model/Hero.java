package cr.ac.ucr.paraiso.model;

public class Hero extends Character {

    private String heroClass;
    private int gold;
    private boolean hasKey;
    private Item[] inventory;

    public Hero(String name, String heroClass) {

        super(name, 100, 20, 0, 0);

        this.heroClass = heroClass;
        this.gold = 0;
        this.hasKey = false;
        this.inventory = new Item[5];

        if (heroClass.equalsIgnoreCase("Tank")) {

            setMaxHealth(150);
            setCurrentHealth(150);
            setAttackPower(10);

        } else if (heroClass.equalsIgnoreCase("Assassin")) {

            setMaxHealth(75);
            setCurrentHealth(75);
            setAttackPower(30);
        }

    }

    public boolean addItem(Item item) {

        for (int i = 0; i < inventory.length; i++) {

            if (inventory[i] == null) {

                inventory[i] = item;
                return true;

            }

        }

        return false;

    }

    public boolean useItem(int index) {

        if (index < 0 || index >= inventory.length) {
            return false;
        }

        if (inventory[index] == null) {
            return false;
        }

        inventory[index].applyEffect(this);

        inventory[index] = null;

        organizeInventory();

        return true;

    }

    private void organizeInventory() {

        Item[] newInventory = new Item[inventory.length];

        int position = 0;

        for (int i = 0; i < inventory.length; i++) {

            if (inventory[i] != null) {

                newInventory[position] = inventory[i];
                position++;

            }

        }

        inventory = newInventory;

    }

    public int getInventorySize() {

        int count = 0;

        for (Item item : inventory) {

            if (item != null) {

                count++;

            }

        }

        return count;

    }

    public Item getItem(int index) {

        if (index < 0 || index >= inventory.length) {

            return null;

        }

        return inventory[index];

    }

    public String getHeroClass() {
        return heroClass;
    }

    public int getGold() {
        return gold;
    }

    public boolean hasKey() {
        return hasKey;
    }

    public Item[] getInventory() {
        return inventory;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

}