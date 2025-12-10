package item;

public class GreaterPotion extends Decorator {
    public GreaterPotion(Item item) {
        super(item);
    }

    @Override
    public String getName() {
        return "Улучшенное " + wrapped.getName();
    }

    @Override
    public void apply(model.Hero h) {
        h.takeDamage(-40); // +40 HP
    }
}
