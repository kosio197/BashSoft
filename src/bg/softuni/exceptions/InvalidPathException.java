package bg.softuni.exceptions;

public class InvalidPathException extends RuntimeException {
    private static final long serialVersionUID = 1095714837383060759L;

    private static final String INVALID_PATH = "The source does not exist.";

    public InvalidPathException() {
        super(INVALID_PATH);
    }

    public InvalidPathException(String message) {
        super(message);
    }
}
