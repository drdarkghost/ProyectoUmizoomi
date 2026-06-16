package cr.ac.ucr.paraiso.model;

public class Hero extends Character {
    private String heroClass;
    private int gold;
    private boolean hasKey;
    private Item[] inventory;

    public Hero(String name, int maxHealth,int attackPower,int posX,int posY,String heroClass) {

            super(name, maxHealth, attackPower, posX, posY);

            this.heroClass = heroClass;
            this.gold = 0;
            this.hasKey = false;
            this.inventory = new Item[5];
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
    }