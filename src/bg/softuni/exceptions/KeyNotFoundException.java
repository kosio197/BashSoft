package bg.softuni.exceptions;

public class KeyNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 756863964072568077L;

    private static final String KEY_NOT_FOUND = "Key not found.";

    public KeyNotFoundException() {
        super(KEY_NOT_FOUND);
    }

    public KeyNotFoundException(String message) {
        super(message);
    }
}
