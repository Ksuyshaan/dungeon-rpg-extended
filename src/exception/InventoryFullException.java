package exception;

public class InventoryFullException extends RuntimeException {
    public InventoryFullException(String msg) {
        super(msg);
    }
}
