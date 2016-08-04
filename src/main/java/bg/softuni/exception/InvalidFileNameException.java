package bg.softuni.exception;

public class InvalidFileNameException extends RuntimeException {
    private static final long serialVersionUID = 1095714837383060759L;

    private static final String FORBIDDEN_SYMBOLS_CONTAINED_IN_NAME = "The given bane contains symbols that are not "
            + "allowed to be used in names of files or folders";

    public InvalidFileNameException() {
        super(FORBIDDEN_SYMBOLS_CONTAINED_IN_NAME);
    }

    public InvalidFileNameException(String message) {
        super(message);
    }
}
