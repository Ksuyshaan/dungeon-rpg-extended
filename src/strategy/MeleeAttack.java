package strategy;

import model.Character; // ✅ Обязательно!

public class MeleeAttack implements AttackStrategy {
    @Override
    public void attack(Character a, Character t) {
        int dmg = a.getAttackPower();
        t.takeDamage(dmg);
        a.notifyObservers("[" + a.getName() + "] нанёс " + dmg + " урона в ближнем бою!");
    }
}