package factory;

import model.Monster;
import strategy.*;

import java.util.Random;

public class MonsterFactory {
    private static final String[] NAMES = {"Гоблин", "Скелет", "Зомби", "Орк"};
    private static final AttackStrategy[] STRATEGIES = {
            new MeleeAttack(), new MagicAttack(), new RangedAttack()
    };
    private final Random rand = new Random();

    public Monster createRandomMonster() {
        String name = NAMES[rand.nextInt(NAMES.length)];
        int hp = 40 + rand.nextInt(30);
        int atk = 8 + rand.nextInt(7);
        int def = 1 + rand.nextInt(3);
        AttackStrategy strat = STRATEGIES[rand.nextInt(STRATEGIES.length)];
        Monster m = new Monster(name, "обычный", hp, atk, def);
        m.setAttackStrategy(strat);
        return m;
    }
}
