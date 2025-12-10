package strategy;
import model.Character;
public interface AttackStrategy {
    void attack(Character attacker, Character target);
}
