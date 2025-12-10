package factory;

import model.Hero;

public abstract class CharacterFactory {
    public abstract Hero createHero(String name);
}
