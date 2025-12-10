package model;


public class Monster extends Character {
    private final String type;

    public Monster(String name, String type, int hp, int attackPower, int defense) {
        super(name, hp, attackPower, defense);
        this.type = type;
    }

    @Override
    public void attack(Character target) {
        if (attackStrategy != null && isAlive() && target.isAlive()) {
            attackStrategy.attack(this, target);
        }
    }

    public String getType() { return type; }
}
