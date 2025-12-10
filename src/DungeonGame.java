import exception.InventoryFullException;
import exception.LowHealthException;
import factory.*;
import item.*;
import model.Hero;
import model.Monster;
import observer.UILogger;

import java.util.*;

public class DungeonGame {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, Integer> stats = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Вы входите в подземелье...");

        System.out.println("Выберите героя:\n1. Воин\n2. Маг\n3. Лучник");
        int ch = sc.nextInt();
        sc.nextLine();
        System.out.print("Введите имя: ");
        String name = sc.nextLine();

        CharacterFactory f = switch (ch) {
            case 1 -> new WarriorFactory();
            case 2 -> new MageFactory();
            case 3 -> new ArcherFactory();
            default -> new WarriorFactory();
        };

        Hero hero = f.createHero(name);
        UILogger logger = new UILogger();
        hero.addObserver(logger);

        MonsterFactory mf = new MonsterFactory();

        Random r = new Random();
        int room = 1;
        try {
            while (hero.isAlive() && room <= 3) {
                System.out.println("\n=== Комната " + room + " ===");
                Monster m = mf.createRandomMonster();
                m.addObserver(logger);
                System.out.println("Вас ждёт " + m.getName() + "!");

                while (hero.isAlive() && m.isAlive()) {
                    System.out.println("\n[" + hero.getName() + "] HP: " + hero.getHp() +
                            " | [" + m.getName() + "] HP: " + m.getHp());
                    System.out.println("Ваш ход (1 — атака, 2 — предмет, 3 — сбежать): ");
                    int act = sc.nextInt();

                    switch (act) {
                        case 1 -> hero.attack(m);
                        case 2 -> {
                            List<Item> inv = hero.getInventory();
                            if (inv.isEmpty()) {
                                System.out.println("Инвентарь пуст!");
                            } else {
                                for (int i = 0; i < inv.size(); i++) {
                                    System.out.println((i + 1) + ". " + inv.get(i).getName());
                                }
                                System.out.print("Выберите предмет: ");
                                int idx = sc.nextInt() - 1;
                                hero.useItem(idx, hero);
                            }
                        }
                        case 3 -> {
                            if (r.nextDouble() < 0.5) {
                                System.out.println("Вы сбежали!");
                                return;
                            } else {
                                System.out.println("Не удалось убежать!");
                            }
                        }
                    }

                    if (m.isAlive()) {
                        m.attack(hero);
                        if (hero.getHp() <= 20) {
                            throw new LowHealthException("Ваше здоровье критическое!");
                        }
                    }
                }

                if (m.isAlive()) {
                    System.out.println("Вы проиграли.");
                    break;
                } else {
                    System.out.println("Победа! Подбираем предмет...");
                    stats.merge(hero.getName(), 1, Integer::sum);
                    try {
                        Item item = new Potion();
                        if (r.nextDouble() < 0.3) {
                            item = new GreaterPotion(item);
                        }
                        if (!hero.addItem(item)) {
                            throw new InventoryFullException("Инвентарь переполнен!");
                        }
                    } catch (InventoryFullException e) {
                        System.out.println(e.getMessage());
                    }
                }
                room++;
            }
        } catch (LowHealthException e) {
            System.out.println("[ALERT] " + e.getMessage());
        }

        System.out.println("\n=== Статистика ===");
        stats.forEach((n, c) -> System.out.println(n + ": " + c + " побед"));
    }
}
