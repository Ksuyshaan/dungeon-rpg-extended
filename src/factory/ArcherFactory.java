package factory;

import model.Hero;
import strategy.RangedAttack;

public class ArcherFactory extends CharacterFactory {
    @Override
    public Hero createHero(String name) {
        Hero h = new Hero(name, 90, 12, 3);
        h.setAttackStrategy(new RangedAttack());
        return h;
    }
}
