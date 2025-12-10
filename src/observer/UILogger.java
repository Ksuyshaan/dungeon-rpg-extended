package observer;

public class UILogger implements GameObserver {
    @Override
    public void onEvent(String event) {
        System.out.println("[UI] " + event);
    }
}
