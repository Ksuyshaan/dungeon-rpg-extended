package factory;

import model.Hero;
import strategy.MagicAttack;

public class MageFactory extends CharacterFactory {
    @Override
    public Hero createHero(String name) {
        Hero h = new Hero(name, 80, 20, 2);
        h.setAttackStrategy(new MagicAttack());
        return h;
    }
}
