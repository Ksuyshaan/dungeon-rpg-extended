package model;

import item.Item;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Character {
    private final List<Item> inventory = new ArrayList<>(5); // вместимость = 5

    public Hero(String name, int hp, int attackPower, int defense) {
        super(name, hp, attackPower, defense);
    }

    @Override
    public void attack(Character target) {
        if (attackStrategy != null && isAlive() && target.isAlive()) {
            attackStrategy.attack(this, target);
        }
    }

    public boolean addItem(Item item) {
        if (inventory.size() >= 5) return false;
        inventory.add(item);
        notifyObservers(name + " подобрал: " + item.getName());
        return true;
    }

    public List<Item> getInventory() {
        return new ArrayList<>(inventory); // возвращаем копию
    }

    public void useItem(int index, Hero hero) {
        if (index >= 0 && index < inventory.size()) {
            Item item = inventory.get(index);
            item.apply(hero);
            inventory.remove(index);
        }
    }
}
