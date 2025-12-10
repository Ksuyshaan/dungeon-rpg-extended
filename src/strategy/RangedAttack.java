package strategy;

import model.Character;
import java.util.Random;

public class RangedAttack implements AttackStrategy {
    private final Random rand = new Random();

    @Override
    public void attack(Character a, Character t) {
        int base = a.getAttackPower();
        boolean crit = rand.nextDouble() < 0.3;
        int dmg = crit ? (int)(base * 1.5) : base;
        t.takeDamage(dmg);
        a.notifyObservers("[" + a.getName() + "] выстрелил на " + dmg + " урона" + (crit ? " (КРИТ!)" : "") + "!");
    }
}
