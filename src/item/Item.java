package item;

import model.Hero;

public interface Item {
    String getName();
    void apply(Hero hero);
}
