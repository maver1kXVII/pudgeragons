package actors;

public abstract class Creature {
    private final String NAME;
    private final Integer BASE_HP;
    private final Integer BASE_ATK;
    private Integer HP;
    private Integer ATK;

    public Creature(String name, Integer hp, Integer atk) {
        this.NAME = name;
        this.BASE_HP = hp;
        this.BASE_ATK = atk;
        this.HP = calculateHP();
        this.ATK = calculateATK();
    }

    private Integer calculateATK() {
        // Here will be ATK modifiers and etc
        return BASE_ATK;
    }

    private Integer calculateHP() {
        // Here will be HP modifiers and etc
        return BASE_HP;
    }

    /**
     * Deals damage to the creature
     * @param takenHP damage value
     * @return true if damage went through
     */
    public Boolean takeDamage(Integer takenHP) {
        // Armor stuff will probably go somewhere there
        if (takenHP > 0 && isAlive()) {
            this.HP -= takenHP;
            if (HP < 0) {
                HP = 0;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Heals a set amount of HP
     * @param healedHP amount of HP healed
     * @return true if heal was successful
     */
    public Boolean heal(Integer healedHP) {
        if (healedHP > 0 && isAlive()) {
            this.HP += healedHP;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Attack another creature
     * @param creature attack target
     */
    public void attack(Creature creature) {
        creature.takeDamage(calculateATK());
    }

    /**
     * Checks if creature is still alive
     * @return true if alive, false - if not
     */
    public Boolean isAlive() {
        return (HP > 0);
    }

    @Override
    public String toString() {
        return this.NAME + " - HP: " + this.HP + ", ATK: " + calculateATK();
    }

    public String getNAME() {
        return NAME;
    }
}
