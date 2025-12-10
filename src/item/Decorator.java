package item;

public abstract class Decorator implements Item {
    protected final Item wrapped;

    public Decorator(Item item) {
        this.wrapped = item;
    }

    @Override
    public String getName() {
        return wrapped.getName();
    }

    @Override
    public void apply(model.Hero hero) {
        wrapped.apply(hero);
    }
}