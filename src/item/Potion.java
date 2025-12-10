package item;

import model.Hero;

public class Potion implements Item {
    @Override
    public String getName() {
        return "Зелье";
    }

    @Override
    public void apply(Hero h) {
        h.takeDamage(-20); // +20 HP
    }
}
