package factory;

import model.Hero;
import strategy.MeleeAttack;

public class WarriorFactory extends CharacterFactory {
    @Override
    public Hero createHero(String name) {
        Hero hero = new Hero(name, 100, 15, 5);
        hero.setAttackStrategy(new MeleeAttack());
        return hero;
    }
}