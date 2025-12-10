package model;

import observer.GameObserver;
import strategy.AttackStrategy;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    protected String name;
    protected int hp;
    protected int attackPower;
    protected int defense;
    protected AttackStrategy attackStrategy;
    private final List<GameObserver> observers = new ArrayList<>();

    public Character(String name, int hp, int attackPower, int defense) {
        this.name = name;
        this.hp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
    }

    public abstract void attack(Character target);

    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - defense);
        hp -= actualDamage;
        notifyObservers(name + " получил " + actualDamage + " урона! HP: " + hp);
        if (hp <= 0) {
            hp = 0;
            notifyObservers(name + " погиб!");
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (GameObserver obs : observers) {
            obs.onEvent(message);
        }
    }

    // Геттеры
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getAttackPower() { return attackPower; }
    public int getDefense() { return defense; }
}
