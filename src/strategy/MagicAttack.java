package strategy;

import model.Character;

public class MagicAttack implements AttackStrategy {
    @Override
    public void attack(Character a, Character t) {
        int dmg = a.getAttackPower() + 5;
        t.takeDamage(dmg);
        a.takeDamage(3); // самоповреждение
        a.notifyObservers("[" + a.getName() + "] применил магию на " + dmg + " урона (потерял 3 HP)!");
    }
}
