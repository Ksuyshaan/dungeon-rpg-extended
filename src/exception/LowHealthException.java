package exception;

public class LowHealthException extends RuntimeException {
    public LowHealthException(String msg) {
        super(msg);
    }
}
